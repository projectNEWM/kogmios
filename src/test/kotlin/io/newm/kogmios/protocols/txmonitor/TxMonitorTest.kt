package io.newm.kogmios.protocols.txmonitor

import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.createTxMonitorClient
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class TxMonitorTest {

    @Test
    fun `test acquire origin`() = runBlocking {
        val client = createTxMonitorClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        )
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()
        // TODO finish test
//        val result = client.hasTx()
//        assertThat(result.result).isInstanceOf(AcquireFailure::class.java)
//        assertThat((result.result as AcquireFailure).acquireFailure.failure).isEqualTo("pointTooOld")
//        client.shutdown()
//        assertThat(client.isConnected).isFalse()
    }
}
