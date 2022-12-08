package io.newm.kogmios.protocols.txmonitor

import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.TxSubmitClient
import io.newm.kogmios.createTxMonitorClient
import io.newm.kogmios.exception.KogmiosException
import io.newm.kogmios.protocols.messages.AwaitAcquireMempoolResponse
import io.newm.kogmios.protocols.messages.SubmitSuccess
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigInteger

class TxMonitorTest {

    @Test
    fun `test await acquire mempool`() = runBlocking {
        val client = createTxMonitorClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        )
        client.use {
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.awaitAcquireMempool()
            assertThat(response.result).isInstanceOf(AwaitAcquireMempoolResponse::class.java)
            assertThat(response.result.awaitAcquired.slot).isGreaterThan(BigInteger.valueOf(14591780L))
        }
        assertThat(client.isConnected).isFalse()
    }

    @Test
    fun `test acquire and release mempool`() = runBlocking {
        createTxMonitorClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.awaitAcquireMempool()
            assertThat(response.result).isInstanceOf(AwaitAcquireMempoolResponse::class.java)
            assertThat(response.result.awaitAcquired.slot).isGreaterThan(BigInteger.valueOf(14591780L))

            val response2 = client.releaseMempool()
            assertThat(response2.result).isEqualTo("Released")
        }
    }

    @Test
    fun `test release mempool without acquire`() = runBlocking {
        createTxMonitorClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val exception = assertThrows<KogmiosException> {
                client.releaseMempool()
            }
            assertThat(exception.message).isEqualTo("'ReleaseMempool' must be called after at least one 'AwaitAcquire'.")
        }
    }

    @Test
    fun `test hasTx with missing tx`() = runBlocking {
        createTxMonitorClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.awaitAcquireMempool()
            assertThat(response.result).isInstanceOf(AwaitAcquireMempoolResponse::class.java)
            assertThat(response.result.awaitAcquired.slot).isGreaterThan(BigInteger.valueOf(14591780L))

            val response1 = client.hasTx("7f042e1a54b9f699961de8a47543d4c4cef0bc5bc5e406194d9952667e2c077d")
            assertThat(response1.result).isFalse()

            val response2 = client.releaseMempool()
            assertThat(response2.result).isEqualTo("Released")
        }
    }

    @Test
    fun `test sizeAndCapacity`() = runBlocking {
        createTxMonitorClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.awaitAcquireMempool()
            assertThat(response.result).isInstanceOf(AwaitAcquireMempoolResponse::class.java)
            assertThat(response.result.awaitAcquired.slot).isGreaterThan(BigInteger.valueOf(14591780L))

            val response1 = client.sizeAndCapacity()
            assertThat(response1.result.capacity).isEqualTo(178176)

            val response2 = client.releaseMempool()
            assertThat(response2.result).isEqualTo("Released")
        }
    }

    @Test
    fun `test nextTx with empty mempool`() = runBlocking {
        createTxMonitorClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.awaitAcquireMempool()
            assertThat(response.result).isInstanceOf(AwaitAcquireMempoolResponse::class.java)
            assertThat(response.result.awaitAcquired.slot).isGreaterThan(BigInteger.valueOf(14591780L))

            var count = 0
            var response1 = client.nextTx()
            while (response1.result != null) {
                assertThat(response1.result).isNotNull()
                count++
                response1 = client.nextTx()
            }
            assertThat(count).isEqualTo(0)

            val response2 = client.releaseMempool()
            assertThat(response2.result).isEqualTo("Released")
        }
    }

    @Test
    @Disabled
    fun `test nextTx with filled mempool`() = runBlocking {
        createTxMonitorClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val submitResponse =
                (client as TxSubmitClient).submit("84a40081825820486e48734313c9e399563d3481696171cef99e6433ea21ee03e383276540f385000181a200581d60da0eb5ed7611482ec5089b69d870e0c56c1c45180256112398e0835b011b0000003d710d8d75021a000298e1031a00e14765a100818258204499320a77997987955eadba91721d5be54ca36536c5448009e822ba3f882d695840fca816ae80096f65402c010053cff395fd64d249b850ead0c2dd21d25e159dfdd335e65f8b5308067c611852057a2f575d91a7e9f9a5e65a015142abd4046c04f5f6")
            assertThat(submitResponse.result).isInstanceOf(SubmitSuccess::class.java)

            val response = client.awaitAcquireMempool()
            assertThat(response.result).isInstanceOf(AwaitAcquireMempoolResponse::class.java)
            assertThat(response.result.awaitAcquired.slot).isGreaterThan(BigInteger.valueOf(14591780L))

            var count = 0
            var response1 = client.nextTx()
            while (response1.result != null) {
                assertThat(response1.result).isNotNull()
                count++
                response1 = client.nextTx()
            }
            assertThat(count).isGreaterThan(0)

            val response2 = client.releaseMempool()
            assertThat(response2.result).isEqualTo("Released")
        }
    }
}
