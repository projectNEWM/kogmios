package io.projectnewm.kogmios.protocols.localstatequery

import com.google.common.truth.Truth.assertThat
import io.projectnewm.kogmios.createStateQueryClient
import io.projectnewm.kogmios.protocols.model.Point
import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class LocalStateQueryTest {

    @Test
    fun `test acquire origin`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "127.0.0.1", websocketPort = 1337)
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
        val client = createStateQueryClient(websocketHost = "127.0.0.1", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val tipResult = client.chainTip()
        val point = Point((tipResult.result as QueryPointDetail).toPointDetail())
        val result = client.acquire(point)
        assertThat(result.result).isInstanceOf(AcquireSuccess::class.java)
        assertThat((result.result as AcquireSuccess).acquireSuccess.point.slot).isEqualTo(point.point.slot)
        assertThat((result.result as AcquireSuccess).acquireSuccess.point.hash).isEqualTo(point.point.hash)
        client.shutdown()
        assertThat(client.isConnected).isFalse()
    }

    @Test
    fun `test acquire and release and acquire`() = runBlocking {
        val client = createStateQueryClient(websocketHost = "127.0.0.1", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()
        val tipResult = client.chainTip()
        val point = Point((tipResult.result as QueryPointDetail).toPointDetail())
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
        val client = createStateQueryClient(websocketHost = "127.0.0.1", websocketPort = 1337)
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
                    val client = createStateQueryClient(websocketHost = "127.0.0.1", websocketPort = 1337, "client$it")
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

}