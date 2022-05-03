package io.projectnewm.kogmios.protocols.localtxmonitor

import com.google.common.truth.Truth.assertThat
import io.projectnewm.kogmios.createLocalTxMonitorClient
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class TxMonitorTest {

    @Test
    fun `test acquire origin`() = runBlocking {
        val client = createLocalTxMonitorClient(websocketHost = "clockwork", websocketPort = 1337)
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
