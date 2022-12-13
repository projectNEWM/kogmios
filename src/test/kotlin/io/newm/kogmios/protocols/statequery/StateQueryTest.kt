package io.newm.kogmios.protocols.statequery

import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.createStateQueryClient
import io.newm.kogmios.protocols.messages.AcquireFailure
import io.newm.kogmios.protocols.messages.AcquireSuccess
import io.newm.kogmios.protocols.messages.MsgQueryResponse
import io.newm.kogmios.protocols.model.*
import kotlinx.coroutines.*
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test

class StateQueryTest {

    @Test
    fun `test acquire origin`() = runBlocking {
        val client = createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        )
        client.use {
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()
            val result = client.acquire()
            assertThat(result.result).isInstanceOf(AcquireFailure::class.java)
            assertThat((result.result as AcquireFailure).acquireFailure.failure).isEqualTo("pointTooOld")
        }
        assertThat(client.isConnected).isFalse()
    }

    @Test
    fun `test acquire tip`() = runBlocking {
        val client = createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        )
        client.use {
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val tipResult: MsgQueryResponse = client.chainTip()
            val point = Point((tipResult.result as QueryPointResult).toPointDetail())
            val result = client.acquire(point)
            assertThat(result.result).isInstanceOf(AcquireSuccess::class.java)
            assertThat((result.result as AcquireSuccess).acquireSuccess.point.slot).isEqualTo(point.point.slot)
            assertThat((result.result as AcquireSuccess).acquireSuccess.point.hash).isEqualTo(point.point.hash)
        }
        assertThat(client.isConnected).isFalse()
    }

    @Test
    fun `test acquire and release and acquire`() = runBlocking {
        val client = createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true
        )
        client.use {
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()
            val tipResult = client.chainTip()
            val point = Point((tipResult.result as QueryPointResult).toPointDetail())
            var result = client.acquire(point)
            assertThat(result.result).isInstanceOf(AcquireSuccess::class.java)
            assertThat((result.result as AcquireSuccess).acquireSuccess.point.slot).isEqualTo(point.point.slot)
            assertThat((result.result as AcquireSuccess).acquireSuccess.point.hash).isEqualTo(point.point.hash)

            val releaseResult = client.release()
            assertThat(releaseResult.result).isEqualTo("Released")

            // re-acquire
            result = client.acquire(point)
            assertThat(result.result).isInstanceOf(AcquireSuccess::class.java)
            assertThat((result.result as AcquireSuccess).acquireSuccess.point.slot).isEqualTo(point.point.slot)
            assertThat((result.result as AcquireSuccess).acquireSuccess.point.hash).isEqualTo(point.point.hash)
        }
        assertThat(client.isConnected).isFalse()
    }

    @Test
    fun `test release without acquire failure`() = runBlocking {
        val client = createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        )
        client.use {
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val releaseResult = client.release()
            // Note: this doesn't actually fail. Ogmios apparently allows us to call
            // release even though we haven't acquired a chain point.
            assertThat(releaseResult.result).isEqualTo("Released")
        }
        assertThat(client.isConnected).isFalse()
    }

    @Test
    fun `test twenty connections`() = runBlocking {
        val deferreds = mutableListOf<Deferred<Unit>>()
        repeat(20) {
            deferreds.add(
                async {
                    delay(it * 100L)
                    val client = createStateQueryClient(
                        websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
                        websocketPort = 443,
                        secure = true,
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
                }
            )
        }
        awaitAll(*deferreds.toTypedArray())
        Unit
    }

    @Test
    fun `test query poolParameters for single pool not found`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.poolParameters(listOf("00000000000000000000000000000000000000000000000000000000"))
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(EmptyQueryResult::class.java)
        }
    }

    @Test
    fun `test query poolParameters for single pool`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.poolParameters(listOf("fa1a5e1e70bc2fa7f60080b214f7bf5ab14c51da4b307a1a792c1714"))
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(QueryPoolParametersResult::class.java)
            assertThat((response.result as QueryPoolParametersResult).size).isEqualTo(1)
            assertThat(response.result as QueryPoolParametersResult).containsKey("pool1lgd9u8nshsh60asqszepfaalt2c5c5w6fvc85xne9st3g3fwtvm")
        }
    }

    @Test
    fun `test query blockHeight`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.blockHeight()
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(LongQueryResult::class.java)
            assertThat((response.result as LongQueryResult).value).isGreaterThan(342915L)
        }
    }

    @Test
    fun `test query currentProtocolParameters`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.currentProtocolParameters()
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(QueryCurrentProtocolParametersResult::class.java)
        }
    }

    @Test
    fun `test query currentEpoch`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.currentEpoch()
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(LongQueryResult::class.java)
            assertThat((response.result as LongQueryResult).value).isGreaterThan(35L)
        }
    }

    @Test
    fun `test query poolIds`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.poolIds()
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(StringArrayQueryResult::class.java)
            assertThat((response.result as StringArrayQueryResult).value.size).isGreaterThan(112)
            assertThat((response.result as StringArrayQueryResult).value[0]).startsWith("pool1")
        }
    }

    @Test
    fun `test query delegationsAndRewards`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val stakeHash = "3a8c70eba78fb47c5a017e38b04254b4e9545b4619cce2aa6b7143b3"
            val response = client.delegationsAndRewards(listOf(stakeHash))
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(QueryDelegationsAndRewardsResult::class.java)
            assertThat((response.result as QueryDelegationsAndRewardsResult).size).isEqualTo(1)
            assertThat((response.result as QueryDelegationsAndRewardsResult)[stakeHash]?.delegate)
                .isEqualTo("pool1lgd9u8nshsh60asqszepfaalt2c5c5w6fvc85xne9st3g3fwtvm")
            assertThat((response.result as QueryDelegationsAndRewardsResult)[stakeHash]?.rewards)
                .isEqualTo(0L)
        }
    }

    @Test
    fun `test query eraStart`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.eraStart()
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(Bound::class.java)
            assertThat(response.result).isEqualTo(Bound(time = 5184000, slot = 3542400, epoch = 12))
        }
    }

    @Test
    fun `test query eraSummaries`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.eraSummaries()
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(EraSummariesQueryResult::class.java)
            println(response.result)
            // the endBound changes depending on the current epoch. Assume it's ok if everything else checks out.
            val endBound = (response.result as EraSummariesQueryResult).value.last().end!!
            assertThat(response.result).isEqualTo(
                EraSummariesQueryResult(
                    listOf(
                        EraSummary(
                            start = Bound(time = 0, slot = 0, epoch = 0),
                            end = Bound(time = 1728000, slot = 86400, epoch = 4),
                            parameters = EraParameters(21600, 20, 4320),
                        ),
                        EraSummary(
                            start = Bound(time = 1728000, slot = 86400, epoch = 4),
                            end = Bound(time = 2160000, slot = 518400, epoch = 5),
                            parameters = EraParameters(432000, 1, 129600),
                        ),
                        EraSummary(
                            start = Bound(time = 2160000, slot = 518400, epoch = 5),
                            end = Bound(time = 2592000, slot = 950400, epoch = 6),
                            parameters = EraParameters(432000, 1, 129600),
                        ),
                        EraSummary(
                            start = Bound(time = 2592000, slot = 950400, epoch = 6),
                            end = Bound(time = 3024000, slot = 1382400, epoch = 7),
                            parameters = EraParameters(432000, 1, 129600),
                        ),
                        EraSummary(
                            start = Bound(time = 3024000, slot = 1382400, epoch = 7),
                            end = Bound(time = 5184000, slot = 3542400, epoch = 12),
                            parameters = EraParameters(432000, 1, 129600),
                        ),
                        EraSummary(
                            start = Bound(time = 5184000, slot = 3542400, epoch = 12),
                            end = endBound,
                            parameters = EraParameters(432000, 1, 129600),
                        )
                    )
                )
            )
        }
    }

    @Test
    fun `test query genesisConfig`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.genesisConfig()
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(CompactGenesis::class.java)
            assertThat((response.result as CompactGenesis).systemStart).isEqualTo(Instant.fromEpochSeconds(1654041600L))
        }
    }

    @Test
    fun `test query nonMyopicMemberRewards`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.nonMyopicMemberRewards(
                listOf(
                    LovelaceInput(1000000000L.toBigInteger()),
                    Blake2bDigestCredential("0bc846c1e00d07c6b49eb36e3236090c01e5a41faab6c2aa95c645f6")
                )
            )
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(QueryNonMyopicMemberRewardsResult::class.java)
            assertThat((response.result as QueryNonMyopicMemberRewardsResult).size).isEqualTo(2)
        }
    }

    @Test
    fun `test query proposedProtocolParameters`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.proposedProtocolParameters()
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(EmptyQueryResult::class.java)
            // FIXME: We don't currently have a way to test until they change a param on preprod. Then we'll
            // have to implement the correct response object.
        }
    }

    @Test
    fun `test query stakeDistribution`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.stakeDistribution()
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(QueryStakeDistributionResult::class.java)
        }
    }

    @Test
    fun `test query systemStart`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.systemStart()
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(InstantQueryResult::class.java)
            assertThat((response.result as InstantQueryResult).value.toEpochMilliseconds()).isEqualTo(1654041600000L)
        }
    }

    @Test
    fun `test query utxoByTxIn`() = runBlocking {
        createStateQueryClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.utxoByTxIn(
                listOf(
                    TxIn(
                        txId = "0b4c56fdae7c23748c837e2443dfebebd020239c107aed9b227851ebabe4a829",
                        index = 1L,
                    ),
                    TxIn(
                        txId = "0b4c56fdae7c23748c837e2443dfebebd020239c107aed9b227851ebabe4a829",
                        index = 2L,
                    ),
                    TxIn(
                        txId = "892e397590da7601e4ccbe113d4d01017083f60cbb2d651f5fb722cee73b0b12",
                        index = 1L,
                    ),
                    TxIn(
                        txId = "892e397590da7601e4ccbe113d4d01017083f60cbb2d651f5fb722cee73b0b12",
                        index = 2L,
                    ),
                    // one with a datum hash
                    TxIn(
                        txId = "35fc6dbcd0bfc2a48949db64e62fa7fc74b916a40b546d7b65bfc830e80035d7",
                        index = 2L,
                    ),
                    // one that is empty/spent
                    TxIn(
                        txId = "017ea264b9aff5660ac646243cca08b4ddf178d61fbad0ec32e5bfde289eae0d",
                        index = 0L,
                    )
                )
            )

            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(UtxoByTxInQueryResult::class.java)
            assertThat((response.result as UtxoByTxInQueryResult).value.size).isEqualTo(4)
        }
    }
}
