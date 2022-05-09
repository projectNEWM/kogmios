package io.projectnewm.kogmios.protocols.localstatequery

import com.google.common.truth.Truth.assertThat
import io.projectnewm.kogmios.createStateQueryClient
import io.projectnewm.kogmios.protocols.localstatequery.model.*
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
                    val client = createStateQueryClient(websocketHost = "clockwork", websocketPort = 1337, "client$it")
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
        assertThat(response.result).isEqualTo(Bound(time = 66528000, slot = 36158400, epoch = 154))
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
                        end = null,
                        parameters = EraParameters(432000, 1, 129600),
                    ),
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
                Blake2bDigestCredential("2545e4c6056511796eed607fbe7db53bc8f88ab5505f3d87ca7c0c5c")
            )
        )
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(QueryNonMyopicMemberRewardsResult::class.java)
        assertThat((response.result as QueryNonMyopicMemberRewardsResult).size).isEqualTo(2)
    }
}
