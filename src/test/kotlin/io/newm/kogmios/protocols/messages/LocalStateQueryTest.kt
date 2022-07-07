package io.newm.kogmios.protocols.messages

import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.createStateQueryClient
import io.newm.kogmios.protocols.model.*
import kotlinx.coroutines.*
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test

class LocalStateQueryTest {

    @Test
    fun `test acquire origin`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()
        val result = client.acquire()
        assertThat(result.result).isInstanceOf(AcquireFailure::class.java)
        assertThat((result.result as AcquireFailure).acquireFailure.failure).isEqualTo("pointTooOld")
        client.shutdown()
        assertThat(client.isConnected).isFalse()
    }

    @Test
    fun `test acquire tip`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val tipResult = client.chainTip()
        val point = Point((tipResult.result as QueryPointResult).toPointDetail())
        val result = client.acquire(point)
        assertThat(result.result).isInstanceOf(AcquireSuccess::class.java)
        assertThat((result.result as AcquireSuccess).acquireSuccess.point.slot).isEqualTo(point.point.slot)
        assertThat((result.result as AcquireSuccess).acquireSuccess.point.hash).isEqualTo(point.point.hash)
        client.shutdown()
        assertThat(client.isConnected).isFalse()
    }

    @Test
    fun `test acquire and release and acquire`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
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

        client.shutdown()
        assertThat(client.isConnected).isFalse()
    }

    @Test
    fun `test release without acquire failure`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val releaseResult = client.release()
        // Note: this doesn't actually fail. Ogmios apparently allows us to call
        // release even though we haven't acquired a chain point.
        assertThat(releaseResult.result).isEqualTo("Released")

        client.shutdown()
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
                        websocketHost = "clockwork",
                        websocketPort = 1337,
                        ogmiosCompact = false,
                        "client$it"
                    )
                    val connectResult = client.connect()
                    assertThat(connectResult).isTrue()
                    assertThat(client.isConnected).isTrue()
                    val tipResult = client.chainTip()
                    assertThat(tipResult).isNotNull()
                    client.shutdown()
                    assertThat(client.isConnected).isFalse()
                }
            )
        }
        awaitAll(*deferreds.toTypedArray())
        Unit
    }

    @Test
    fun `test query poolParameters for single pool not found`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.poolParameters(listOf("00000000000000000000000000000000000000000000000000000000"))
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(EmptyQueryResult::class.java)
    }

    @Test
    fun `test query poolParameters for single pool`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.poolParameters(listOf("3299895e62b13de5a5a52f4bb5726db5fb38928c8d2c21ff8d78517d"))
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(QueryPoolParametersResult::class.java)
        assertThat((response.result as QueryPoolParametersResult).size).isEqualTo(1)
        assertThat(response.result as QueryPoolParametersResult).containsKey("pool1x2vcjhnzky77tfd99a9m2undkhan3y5v35kzrlud0pgh6nsdw0z")
    }

    @Test
    fun `test query blockHeight`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.blockHeight()
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(LongQueryResult::class.java)
        assertThat((response.result as LongQueryResult).value).isGreaterThan(3421886L)
    }

    @Test
    fun `test query currentProtocolParameters`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.currentProtocolParameters()
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(QueryCurrentProtocolParametersResult::class.java)
    }

    @Test
    fun `test query currentEpoch`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.currentEpoch()
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(LongQueryResult::class.java)
        assertThat((response.result as LongQueryResult).value).isGreaterThan(196L)
    }

    @Test
    fun `test query poolIds`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.poolIds()
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(StringArrayQueryResult::class.java)
        assertThat((response.result as StringArrayQueryResult).value.size).isGreaterThan(1245)
        assertThat((response.result as StringArrayQueryResult).value[0]).startsWith("pool1")
    }

    @Test
    fun `test query delegationsAndRewards`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val stakeHash = "db9927472f4a99e2de84cfae3b3b128d62e7792e7c36d2b2bbd08f7d"
        val response = client.delegationsAndRewards(listOf(stakeHash))
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(QueryDelegationsAndRewardsResult::class.java)
        assertThat((response.result as QueryDelegationsAndRewardsResult).size).isEqualTo(1)
        assertThat((response.result as QueryDelegationsAndRewardsResult)[stakeHash]?.delegate)
            .isEqualTo("pool1x2vcjhnzky77tfd99a9m2undkhan3y5v35kzrlud0pgh6nsdw0z")
        assertThat((response.result as QueryDelegationsAndRewardsResult)[stakeHash]?.rewards)
            .isEqualTo(0L)
    }

    @Test
    fun `test query eraStart`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.eraStart()
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(Bound::class.java)
        assertThat(response.result).isEqualTo(Bound(time = 92880000, slot = 62510400, epoch = 215))
    }

    @Test
    fun `test query eraSummaries`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.eraSummaries()
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(EraSummariesQueryResult::class.java)
        println(response.result)
        assertThat(response.result).isEqualTo(
            EraSummariesQueryResult(
                listOf(
                    EraSummary(
                        start = Bound(0, 0, 0),
                        end = Bound(31968000, 1598400, 74),
                        parameters = EraParameters(21600, 20, 4320),
                    ),
                    EraSummary(
                        start = Bound(31968000, 1598400, 74),
                        end = Bound(44064000, 13694400, 102),
                        parameters = EraParameters(432000, 1, 129600),
                    ),
                    EraSummary(
                        start = Bound(44064000, 13694400, 102),
                        end = Bound(48384000, 18014400, 112),
                        parameters = EraParameters(432000, 1, 129600),
                    ),
                    EraSummary(
                        start = Bound(48384000, 18014400, 112),
                        end = Bound(66528000, 36158400, 154),
                        parameters = EraParameters(432000, 1, 129600),
                    ),
                    EraSummary(
                        start = Bound(66528000, 36158400, 154),
                        end = Bound(time = 92880000, slot = 62510400, epoch = 215),
                        parameters = EraParameters(432000, 1, 129600),
                    ),
                    EraSummary(
                        start = Bound(time = 92880000, slot = 62510400, epoch = 215),
                        end = null,
                        parameters = EraParameters(432000, 1, 129600),
                    )
                )
            )
        )
    }

    @Test
    fun `test query genesisConfig`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.genesisConfig()
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(CompactGenesis::class.java)
        assertThat((response.result as CompactGenesis).systemStart).isEqualTo(Instant.fromEpochSeconds(1563999616L))
    }

    @Test
    fun `test query nonMyopicMemberRewards`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
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

    @Test
    fun `test query proposedProtocolParameters`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.proposedProtocolParameters()
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(EmptyQueryResult::class.java)
        // FIXME: We don't currently have a way to test until they change a param on testnet. Then we'll
        // have to implement the correct response object.
    }

    @Test
    fun `test query stakeDistribution`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.stakeDistribution()
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(QueryStakeDistributionResult::class.java)
    }

    @Test
    fun `test query systemStart`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.systemStart()
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(InstantQueryResult::class.java)
        assertThat((response.result as InstantQueryResult).value.toEpochMilliseconds()).isEqualTo(1563999616000L)
    }

    @Test
    fun `test query utxoByTxIn`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.utxoByTxIn(
            listOf(
                TxIn(
                    txId = "244fc52beb24cda13ddcc1ece701e81bc17f78e0f76099230cc1274ed9ebb339",
                    index = 1L,
                ),
                TxIn(
                    txId = "244fc52beb24cda13ddcc1ece701e81bc17f78e0f76099230cc1274ed9ebb339",
                    index = 2L,
                ),
                TxIn(
                    txId = "b2dae597ba2c91959d3d34599d1d859fa09d4598b0b10935b4ddd512bc58920b",
                    index = 1L,
                ),
                TxIn(
                    txId = "b06f6d9c13a1687310a398144a05e2f1798739291e07d2b2d38649a27e4f6a8f",
                    index = 9L,
                ),
                // one with a datum hash
                TxIn(
                    txId = "575a1a55ac96a5cc5dee08a120b3ff8bddd540f9fd6dd229e2ab049c589704b0",
                    index = 0L,
                ),
                // one that is empty/spent
                TxIn(
                    txId = "b2bcb553bc5ebc149cce792ef3ad2957bea10919039657e4d6a140e9c71bb7fe",
                    index = 1L,
                )
            )
        )

        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(UtxoByTxInQueryResult::class.java)
        assertThat((response.result as UtxoByTxInQueryResult).value.size).isEqualTo(4)
    }
}
