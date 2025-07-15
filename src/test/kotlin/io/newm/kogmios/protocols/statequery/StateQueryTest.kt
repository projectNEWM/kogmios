package io.newm.kogmios.protocols.statequery

import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.createStateQueryClient
import io.newm.kogmios.exception.KogmiosException
import io.newm.kogmios.protocols.model.AdaRewardsInput
import io.newm.kogmios.protocols.model.EraParameters
import io.newm.kogmios.protocols.model.EraSummary
import io.newm.kogmios.protocols.model.GenesisEra
import io.newm.kogmios.protocols.model.Lovelace
import io.newm.kogmios.protocols.model.Milliseconds
import io.newm.kogmios.protocols.model.Origin
import io.newm.kogmios.protocols.model.ParamsProjectedRewards
import io.newm.kogmios.protocols.model.ParamsUtxoByAddresses
import io.newm.kogmios.protocols.model.ParamsUtxoByOutputReferences
import io.newm.kogmios.protocols.model.Point
import io.newm.kogmios.protocols.model.Seconds
import io.newm.kogmios.protocols.model.Transaction
import io.newm.kogmios.protocols.model.UtxoOutputReference
import io.newm.kogmios.protocols.model.fault.StringFaultData
import io.newm.kogmios.protocols.model.result.Bound
import io.newm.kogmios.protocols.model.result.LiveStakeDistributionResult
import io.newm.kogmios.protocols.model.result.LongResult
import io.newm.kogmios.protocols.model.result.ProjectedRewardsResult
import io.newm.kogmios.protocols.model.result.RewardAccountSummariesResult
import io.newm.kogmios.protocols.model.result.StakePoolsResult
import io.newm.kogmios.protocols.model.result.UtxoResult
import java.io.IOException
import java.math.BigInteger
import kotlin.time.Instant
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StateQueryTest {
    companion object {
        // local testing
        private const val TEST_HOST = "localhost"
        private const val TEST_PORT = 1337
        private const val TEST_SECURE = false

        // remote testing
//        private const val TEST_HOST = "ogmios-kogmios-9ab819.us1.demeter.run"
//        private const val TEST_PORT = 443
//        private const val TEST_SECURE = true
    }

    @Test
    fun `test health`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.health()
                assertThat(response).isNotNull()
                assertThat(response.connectionStatus).isEqualTo("connected")
                println(response)
            }
        }

    @Test
    fun `test acquire origin`() =
        runBlocking {
            val client =
                createStateQueryClient(
                    websocketHost = TEST_HOST,
                    websocketPort = TEST_PORT,
                    secure = TEST_SECURE,
                )
            client.use {
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()
                val exception =
                    assertThrows<KogmiosException> {
                        client.acquire(pointOrOrigin = Origin())
                    }
                assertThat(exception.message).isEqualTo("Failed to acquire requested point.")
                assertThat(exception.cause).isInstanceOf(IOException::class.java)
                assertThat(exception.jsonRpcErrorResponse.error.code).isEqualTo(2000)
                assertThat(exception.jsonRpcErrorResponse.error.message).isEqualTo("Failed to acquire requested point.")
                assertThat((exception.jsonRpcErrorResponse.error.data as StringFaultData).value).isEqualTo("Target point is too old.")
            }
            assertThat(client.isConnected).isFalse()
        }

    @Test
    fun `test acquire tip`() =
        runBlocking {
            val client =
                createStateQueryClient(
                    websocketHost = TEST_HOST,
                    websocketPort = TEST_PORT,
                    secure = TEST_SECURE,
                )
            client.use {
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.chainTip()
                assertThat(response.result).isInstanceOf(io.newm.kogmios.protocols.model.result.TipResult::class.java)
                val point = Point(response.result.toPointDetail())
                val acquireResponse = client.acquire(point)
                assertThat(acquireResponse.result.point.slot).isEqualTo(point.point.slot)
                assertThat(acquireResponse.result.point.id).isEqualTo(point.point.id)
            }
            assertThat(client.isConnected).isFalse()
        }

    @Test
    fun `test acquire and release and acquire`() =
        runBlocking {
            val client =
                createStateQueryClient(
                    websocketHost = TEST_HOST,
                    websocketPort = TEST_PORT,
                    secure = TEST_SECURE,
                )
            client.use {
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()
                val tipResult = client.chainTip()
                val point = Point(tipResult.result.toPointDetail())
                var result = client.acquire(point)
                assertThat(result.result.point.slot).isEqualTo(point.point.slot)
                assertThat(result.result.point.id).isEqualTo(point.point.id)

                val releaseResult = client.release()
                assertThat(releaseResult.result.released).isEqualTo("ledgerState")

                // re-acquire
                result = client.acquire(point)
                assertThat(result.result.point.slot).isEqualTo(point.point.slot)
                assertThat(result.result.point.id).isEqualTo(point.point.id)
            }
            assertThat(client.isConnected).isFalse()
        }

    @Test
    fun `test release without acquire failure`() =
        runBlocking {
            val client =
                createStateQueryClient(
                    websocketHost = TEST_HOST,
                    websocketPort = TEST_PORT,
                    secure = TEST_SECURE,
                )
            client.use {
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val releaseResult = client.release()
                // Note: this doesn't actually fail. Ogmios apparently allows us to call
                // release even though we haven't acquired a chain point.
                assertThat(releaseResult.result.released).isEqualTo("ledgerState")
            }
            assertThat(client.isConnected).isFalse()
        }

    @Test
    fun `test twenty connections`() =
        runBlocking {
            val deferreds = mutableListOf<Deferred<Unit>>()
            repeat(20) {
                deferreds.add(
                    async {
                        delay(it * 100L)
                        val client =
                            createStateQueryClient(
                                websocketHost = TEST_HOST,
                                websocketPort = TEST_PORT,
                                secure = TEST_SECURE,
                                ogmiosCompact = false,
                                loggerName = "client$it",
                            )
                        client.use {
                            val connectResult = client.connect()
                            assertThat(connectResult).isTrue()
                            assertThat(client.isConnected).isTrue()
                            val tipResult = client.chainTip()
                            assertThat(tipResult).isNotNull()
                        }
                        assertThat(client.isConnected).isFalse()
                    },
                )
            }
            awaitAll(*deferreds.toTypedArray())
            Unit
        }

    @Test
    fun `test query pools for single pool not found`() =
        runBlocking {
            val client =
                createStateQueryClient(
                    websocketHost = TEST_HOST,
                    websocketPort = TEST_PORT,
                    secure = TEST_SECURE,
                )
            client.use {
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.stakePools(listOf("00000000000000000000000000000000000000000000000000000000"))
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(StakePoolsResult::class.java)
                assertThat(response.result).isEmpty()
            }
            assertThat(client.isConnected).isFalse()
        }

    @Test
    fun `test query pools for single pool`() =
        runBlocking {
            val client =
                createStateQueryClient(
                    websocketHost = TEST_HOST,
                    websocketPort = TEST_PORT,
                    secure = TEST_SECURE,
                )
            client.use {
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.stakePools(listOf("pool1lgd9u8nshsh60asqszepfaalt2c5c5w6fvc85xne9st3g3fwtvm"))
                println("response: $response")
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(StakePoolsResult::class.java)
                assertThat(response.result.size).isEqualTo(1)
                assertThat(response.result).containsKey("pool1lgd9u8nshsh60asqszepfaalt2c5c5w6fvc85xne9st3g3fwtvm")
            }
        }

    @Test
    fun `test query blockHeight`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.blockHeight()
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(LongResult::class.java)
                assertThat(response.result.value).isGreaterThan(342915L)
            }
        }

    @Test
    fun `test query protocolParameters`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.protocolParameters()
                println("response: $response")
                assertThat(response).isNotNull()
                assertThat(response.result.minFeeConstant.ada.lovelace).isEqualTo(155381.toBigInteger())
                assertThat(response.result.minFeeCoefficient).isEqualTo(44.toBigInteger())
                assertThat(response.result.minUtxoDepositCoefficient).isEqualTo(4310.toBigInteger())
            }
        }

    @Test
    fun `test query epoch`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.epoch()
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(LongResult::class.java)
                assertThat(response.result.value).isGreaterThan(84L)
            }
        }

    @Test
    fun `test query rewardAccountSummaries`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val stakeHash = "3a8c70eba78fb47c5a017e38b04254b4e9545b4619cce2aa6b7143b3"
                val response = client.rewardAccountSummaries(listOf(stakeHash))
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(RewardAccountSummariesResult::class.java)
                assertThat(response.result.size).isEqualTo(1)
                assertThat(response.result[stakeHash]!!.delegate.id).isEqualTo("pool1lgd9u8nshsh60asqszepfaalt2c5c5w6fvc85xne9st3g3fwtvm")
                assertThat(
                    response.result[stakeHash]!!
                        .rewards.ada.lovelace
                ).isEqualTo(BigInteger.ZERO)
            }
        }

    @Test
    fun `test query eraStart`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.eraStart()
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(Bound::class.java)
                assertThat(response.result).isEqualTo(
                    Bound(
                        time = Seconds(70416000.toBigInteger()),
                        slot = 68774400,
                        epoch = 163
                    )
                )
            }
        }

    @Test
    fun `test query eraSummaries`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.eraSummaries()
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(io.newm.kogmios.protocols.model.result.EraSummariesResult::class.java)
                println(response.result)
                // the endBound changes depending on the current epoch. Assume it's ok if everything else checks out.
                val endBound = response.result.last().end!!
                assertThat(response.result)
                    .containsExactly(
                        EraSummary(
                            start = Bound(time = Seconds(0.toBigInteger()), slot = 0, epoch = 0),
                            end = Bound(time = Seconds(1728000.toBigInteger()), slot = 86400, epoch = 4),
                            parameters = EraParameters(21600, Milliseconds(20000.toBigInteger()), 4320),
                        ),
                        EraSummary(
                            start = Bound(time = Seconds(1728000.toBigInteger()), slot = 86400, epoch = 4),
                            end = Bound(time = Seconds(2160000.toBigInteger()), slot = 518400, epoch = 5),
                            parameters = EraParameters(432000, Milliseconds(1000.toBigInteger()), 129600),
                        ),
                        EraSummary(
                            start = Bound(time = Seconds(2160000.toBigInteger()), slot = 518400, epoch = 5),
                            end = Bound(time = Seconds(2592000.toBigInteger()), slot = 950400, epoch = 6),
                            parameters = EraParameters(432000, Milliseconds(1000.toBigInteger()), 129600),
                        ),
                        EraSummary(
                            start = Bound(time = Seconds(2592000.toBigInteger()), slot = 950400, epoch = 6),
                            end = Bound(time = Seconds(3024000.toBigInteger()), slot = 1382400, epoch = 7),
                            parameters = EraParameters(432000, Milliseconds(1000.toBigInteger()), 129600),
                        ),
                        EraSummary(
                            start = Bound(time = Seconds(3024000.toBigInteger()), slot = 1382400, epoch = 7),
                            end = Bound(time = Seconds(5184000.toBigInteger()), slot = 3542400, epoch = 12),
                            parameters = EraParameters(432000, Milliseconds(1000.toBigInteger()), 129600),
                        ),
                        EraSummary(
                            start = Bound(time = Seconds(5184000.toBigInteger()), slot = 3542400, epoch = 12),
                            end = Bound(time = Seconds(70416000.toBigInteger()), slot = 68774400, epoch = 163),
                            parameters = EraParameters(432000, Milliseconds(1000.toBigInteger()), 129600),
                        ),
                        EraSummary(
                            start = Bound(time = Seconds(70416000.toBigInteger()), slot = 68774400, epoch = 163),
                            end = endBound,
                            parameters = EraParameters(432000, Milliseconds(1000.toBigInteger()), 129600),
                        ),
                    ).inOrder()
            }
        }

    @Test
    fun `test query genesisConfig byron`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.genesisConfig(GenesisEra.BYRON)
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(io.newm.kogmios.protocols.model.result.ByronGenesisConfigResult::class.java)
                assertThat((response.result as io.newm.kogmios.protocols.model.result.ByronGenesisConfigResult).startTime).isEqualTo(
                    Instant.fromEpochSeconds(
                        1654041600L
                    )
                )
            }
        }

    @Test
    fun `test query genesisConfig shelley`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.genesisConfig(GenesisEra.SHELLEY)
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(io.newm.kogmios.protocols.model.result.ShelleyGenesisConfigResult::class.java)
                assertThat((response.result as io.newm.kogmios.protocols.model.result.ShelleyGenesisConfigResult).startTime).isEqualTo(
                    Instant.fromEpochSeconds(
                        1654041600L
                    )
                )
            }
        }

    @Test
    fun `test query genesisConfig alonzo`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.genesisConfig(GenesisEra.ALONZO)
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(io.newm.kogmios.protocols.model.result.AlonzoGenesisConfigResult::class.java)
                assertThat(
                    (response.result as io.newm.kogmios.protocols.model.result.AlonzoGenesisConfigResult)
                        .updatableParameters.plutusCostModels.plutusV1
                        ?.get(
                            4
                        )
                ).isEqualTo(
                    396231.toBigInteger()
                )
            }
        }

    @Test
    fun `test query genesisConfig conway`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.genesisConfig(GenesisEra.CONWAY)
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(io.newm.kogmios.protocols.model.result.ConwayGenesisConfigResult::class.java)
                assertThat(
                    (response.result as io.newm.kogmios.protocols.model.result.ConwayGenesisConfigResult)
                        .updatableParameters.governanceActionDeposit.ada.lovelace
                ).isEqualTo(100000000000L.toBigInteger())
            }
        }

    @Test
    fun `test query projectedRewards`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.projectedRewards(
                        ParamsProjectedRewards(
                            stake = listOf(AdaRewardsInput(Lovelace(1000000000L.toBigInteger()))),
                            scripts = emptyList(),
                            keys = listOf("0bc846c1e00d07c6b49eb36e3236090c01e5a41faab6c2aa95c645f6")
                        )
                    )
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(ProjectedRewardsResult::class.java)
                assertThat(response.result.size).isEqualTo(2)
            }
        }

    @Test
    fun `test query proposedProtocolParameters`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.proposedProtocolParameters()
                assertThat(response).isNotNull()
                assertThat(
                    response.result
                ).isInstanceOf(io.newm.kogmios.protocols.model.result.ProposedProtocolParametersResult::class.java)
                // FIXME: We don't currently have a way to test until they change a param on preprod. Then we'll
                // have to implement the correct response object.
            }
        }

    @Test
    fun `test query liveStakeDistribution`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.liveStakeDistribution()
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(LiveStakeDistributionResult::class.java)
            }
        }

    @Test
    fun `test query networkStartTime`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.networkStartTime()
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(io.newm.kogmios.protocols.model.result.InstantResult::class.java)
                println(response.result.value)
                println(Instant.parse("2022-06-01T00:00:00Z"))
                assertThat(response.result.value).isEqualTo(Instant.parse("2022-06-01T00:00:00Z"))
            }
        }

    @Test
    fun `test query utxoByOutputReferences`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.utxo(
                        params =
                            ParamsUtxoByOutputReferences(
                                outputReferences =
                                    listOf(
                                        UtxoOutputReference(
                                            Transaction(id = "0b4c56fdae7c23748c837e2443dfebebd020239c107aed9b227851ebabe4a829"),
                                            index = 1L,
                                        ),
                                        UtxoOutputReference(
                                            Transaction(id = "0b4c56fdae7c23748c837e2443dfebebd020239c107aed9b227851ebabe4a829"),
                                            index = 2L,
                                        ),
                                        UtxoOutputReference(
                                            Transaction(id = "892e397590da7601e4ccbe113d4d01017083f60cbb2d651f5fb722cee73b0b12"),
                                            index = 1L,
                                        ),
                                        UtxoOutputReference(
                                            Transaction(id = "892e397590da7601e4ccbe113d4d01017083f60cbb2d651f5fb722cee73b0b12"),
                                            index = 2L,
                                        ),
                                        // one with a datum hash
                                        UtxoOutputReference(
                                            Transaction(id = "35fc6dbcd0bfc2a48949db64e62fa7fc74b916a40b546d7b65bfc830e80035d7"),
                                            index = 2L,
                                        ),
                                        // one that is empty/spent
                                        UtxoOutputReference(
                                            Transaction(id = "017ea264b9aff5660ac646243cca08b4ddf178d61fbad0ec32e5bfde289eae0d"),
                                            index = 0L,
                                        ),
                                        // one that has tokens on it
                                        UtxoOutputReference(
                                            Transaction(id = "c984edc2d9e6d50e152284a83c3934c112661dc3a73fc51c3b5d65b22f4fc72d"),
                                            index = 0L,
                                        ),
                                        // one with a simple script
                                        UtxoOutputReference(
                                            Transaction(id = "ece29f5032e2afc057ec710d7f1f4e52eda1544b7fd6d2925db6ece72bc68a4b"),
                                            index = 0L,
                                        ),
                                        // one with a crazy native script
                                        UtxoOutputReference(
                                            Transaction(id = "2004673b7d32164c9c21d8f664b4310ee0ca30fd9cd5db73a169fb6c4bb350ee"),
                                            index = 0L,
                                        ),
                                    )
                            )
                    )

                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(UtxoResult::class.java)
                assertThat(response.result.size).isEqualTo(7)
            }
        }

    @Test
    fun `test query utxoByAddresses`() =
        runBlocking {
            createStateQueryClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
                ogmiosCompact = false,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.utxo(
                        params =
                            ParamsUtxoByAddresses(
                                listOf(
                                    "addr_test1vrdqad0dwcg5stk9pzdknkrsurzkc8z9rqp9vyfrnrsgxkc4r8za2",
                                    "addr_test1vqpqvhh4am6lqh99068n3eu2kp6zyx0ng7g8wa3c0z888rgk2dz6k",
                                    "addr_test1qpwrm04e7fhalvw28ct274c2cfxqg5h3m3d8s0mnsn2xdtp633cwhfu0k3795qt78zcyy495a929k3seen3256m3gwes6t2fxt",
                                )
                            )
                    )
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(UtxoResult::class.java)
            }
        }
}
