package io.newm.kogmios.protocols.txmonitor

import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.TxSubmitClient
import io.newm.kogmios.createTxMonitorClient
import io.newm.kogmios.exception.KogmiosException
import io.newm.kogmios.protocols.model.fault.MustAcquireMempoolFirstFault
import io.newm.kogmios.protocols.model.result.AcquireMempoolResult
import io.newm.kogmios.protocols.model.result.ReleaseMempoolResult
import io.newm.kogmios.protocols.model.result.SubmitTxResult
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigInteger

class TxMonitorTest {
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
    fun `test acquire mempool`() =
        runBlocking {
            val client =
                createTxMonitorClient(
                    websocketHost = TEST_HOST,
                    websocketPort = TEST_PORT,
                    secure = TEST_SECURE,
                )
            client.use {
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.acquireMempool()
                assertThat(response.result).isInstanceOf(AcquireMempoolResult::class.java)
                assertThat(response.result.slot).isGreaterThan(BigInteger.valueOf(14591780L))
            }
            assertThat(client.isConnected).isFalse()
        }

    @Test
    fun `test acquire and release mempool`() =
        runBlocking {
            createTxMonitorClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.acquireMempool()
                assertThat(response.result).isInstanceOf(AcquireMempoolResult::class.java)
                assertThat(response.result.slot).isGreaterThan(BigInteger.valueOf(14591780L))

                val response2 = client.releaseMempool()
                assertThat(response2.result).isInstanceOf(ReleaseMempoolResult::class.java)
                assertThat(response2.result.released).isEqualTo("mempool")
            }
        }

    @Test
    fun `test release mempool without acquire`() =
        runBlocking {
            createTxMonitorClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val exception =
                    assertThrows<KogmiosException> {
                        client.releaseMempool()
                    }
                assertThat(exception.message).isEqualTo("You must acquire a mempool snapshot prior to accessing it.")
                assertThat(exception.jsonRpcErrorResponse).isNotNull()
                assertThat(exception.jsonRpcErrorResponse.error).isInstanceOf(MustAcquireMempoolFirstFault::class.java)
                assertThat(exception.jsonRpcErrorResponse.error.code).isEqualTo(4000)
                assertThat(
                    exception.jsonRpcErrorResponse.error.message
                ).isEqualTo("You must acquire a mempool snapshot prior to accessing it.")
                assertThat(exception.jsonRpcErrorResponse.error.data).isNull()
            }
        }

    @Test
    fun `test hasTx with missing tx`() =
        runBlocking {
            createTxMonitorClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.acquireMempool()
                assertThat(response.result).isInstanceOf(AcquireMempoolResult::class.java)
                assertThat(response.result.slot).isGreaterThan(BigInteger.valueOf(14591780L))

                val response1 = client.hasTransaction("7f042e1a54b9f699961de8a47543d4c4cef0bc5bc5e406194d9952667e2c077d")
                assertThat(response1.result.value).isFalse()

                val response2 = client.releaseMempool()
                assertThat(response2.result.released).isEqualTo("mempool")
            }
        }

    @Test
    fun `test sizeOfMempool`() =
        runBlocking {
            createTxMonitorClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.acquireMempool()
                assertThat(response.result).isInstanceOf(AcquireMempoolResult::class.java)
                assertThat(response.result.slot).isGreaterThan(BigInteger.valueOf(14591780L))

                val response1 = client.sizeOfMempool()
                assertThat(response1.result.maxCapacity.bytes).isEqualTo(178176.toBigInteger())

                val response2 = client.releaseMempool()
                assertThat(response2.result.released).isEqualTo("mempool")
            }
        }

    @Test
    fun `test nextTransaction with empty mempool`() =
        runBlocking {
            createTxMonitorClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response = client.acquireMempool()
                assertThat(response.result).isInstanceOf(AcquireMempoolResult::class.java)
                assertThat(response.result.slot).isGreaterThan(BigInteger.valueOf(14591780L))

                var count = 0
                var response1 = client.nextTransaction()
                while (response1.result.transaction != null) {
                    count++
                    response1 = client.nextTransaction()
                }
                assertThat(count).isEqualTo(0)

                val response2 = client.releaseMempool()
                assertThat(response2.result.released).isEqualTo("mempool")
            }
        }

    @Test
    @Disabled
    fun `test nextTransaction with filled mempool`() =
        runBlocking {
            createTxMonitorClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val submitResponse =
                    (client as TxSubmitClient).submit(
                        "84a4008382582069b5b8fd259386a1d3d53c6f3fd850c6ef166ec64dee5ef3e6444e5e5924f9bb00825820b831f224dfcbf5dd0e0012a491d84579de436d2d4a1a251c88001c83cad2047400825820b831f224dfcbf5dd0e0012a491d84579de436d2d4a1a251c88001c83cad2047401018282581d60da0eb5ed7611482ec5089b69d870e0c56c1c45180256112398e0835b1a000f424082581d60da0eb5ed7611482ec5089b69d870e0c56c1c45180256112398e0835b1b00000223ea46ac2e021a0002beb1031a02fb601fa100818258204499320a77997987955eadba91721d5be54ca36536c5448009e822ba3f882d69584085ea0cafc7adb6e8f3cc6ac94a43b626e8a0e76f39abd336d9e41f7ea334d97a69fc921e9c87415488a507a1c14ebbcf3de89a69f6c09a8e8835a13826ff8c0df5f6"
                    )
                assertThat(submitResponse.result).isInstanceOf(SubmitTxResult::class.java)

                val response = client.acquireMempool()
                assertThat(response.result).isInstanceOf(AcquireMempoolResult::class.java)
                assertThat(response.result.slot).isGreaterThan(BigInteger.valueOf(14591780L))

                var count = 0
                var response1 = client.nextTransaction()
                while (response1.result.transaction != null) {
                    println("mempool transaction: ${response1.result.transaction}")
                    count++
                    response1 = client.nextTransaction()
                }
                assertThat(count).isGreaterThan(0)

                val response2 = client.releaseMempool()
                assertThat(response2.result.released).isEqualTo("mempool")
            }
        }
}
