package io.newm.kogmios.protocols.chainsync

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.Client.Companion.DEFAULT_REQUEST_TIMEOUT_MS
import io.newm.kogmios.Client.Companion.INFINITE_REQUEST_TIMEOUT_MS
import io.newm.kogmios.ClientImpl
import io.newm.kogmios.createChainSyncClient
import io.newm.kogmios.exception.KogmiosException
import io.newm.kogmios.protocols.model.BlockPraos
import io.newm.kogmios.protocols.model.OriginString
import io.newm.kogmios.protocols.model.PointDetail
import io.newm.kogmios.protocols.model.TransactionMetadata
import io.newm.kogmios.protocols.model.fault.IntersectionNotFoundFault
import io.newm.kogmios.protocols.model.result.IntersectionFoundResult
import io.newm.kogmios.protocols.model.result.RollBackward
import io.newm.kogmios.protocols.model.result.RollForward
import java.time.Instant
import kotlin.math.floor
import kotlin.math.max
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.LoggerFactory

class ChainSyncTest {
    companion object {
        // local testing - preprod
        private const val TEST_HOST = "localhost"
        private const val TEST_PORT = 1337
        private const val TEST_SECURE = false

        // local testing - mainnet
//        private const val TEST_HOST = "localhost"
//        private const val TEST_PORT = 1338
//        private const val TEST_SECURE = false

        // remote testing
//        private const val TEST_HOST = "ogmios-kogmios-9ab819.us1.demeter.run"
//        private const val TEST_PORT = 443
//        private const val TEST_SECURE = true
    }

    @Test
    fun `test FindIntersect origin`() =
        runBlocking {
            createChainSyncClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.findIntersect(
                        listOf(
                            OriginString(),
                        ),
                    )
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(IntersectionFoundResult::class.java)
            }
        }

    @Test
    fun `test FindIntersect shelley start`() =
        runBlocking {
            createChainSyncClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.findIntersect(
                        listOf(
                            // last byron block on preprod
                            PointDetail(
                                slot = 84242L,
                                id = "45899e8002b27df291e09188bfe3aeb5397ac03546a7d0ead93aa2500860f1af",
                            ),
                            // last byron block on old testnet
                            // PointDetail(slot = 1598399L, hash = "7e16781b40ebf8b6da18f7b5e8ade855d6738095ef2f1c58c77e88b6e45997a4")
                        ),
                    )
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(IntersectionFoundResult::class.java)
                assertThat((response.result.intersection as PointDetail).slot).isEqualTo(
                    84242L,
                )
            }
        }

    @Test
    fun `test FindIntersect not found`() =
        runBlocking {
            createChainSyncClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val exception =
                    assertThrows<KogmiosException> {
                        client.findIntersect(
                            listOf(
                                PointDetail(
                                    slot = 99999999999L,
                                    id = "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
                                ),
                            ),
                        )
                    }
                assertThat(exception.message).isEqualTo("No intersection found.")
                assertThat(exception.jsonRpcErrorResponse.error).isInstanceOf(IntersectionNotFoundFault::class.java)
                assertThat((exception.jsonRpcErrorResponse.error as IntersectionNotFoundFault).data.tip.slot).isGreaterThan(
                    14155704L
                )
            }
        }

    @Test
    fun `test RequestNext shelley block`() =
        runBlocking {
            createChainSyncClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.findIntersect(
                        listOf(
                            PointDetail(
                                slot = 84242L,
                                id = "45899e8002b27df291e09188bfe3aeb5397ac03546a7d0ead93aa2500860f1af",
                            ),
                        ),
                    )
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(IntersectionFoundResult::class.java)
                assertThat((response.result.intersection as PointDetail).slot).isEqualTo(
                    84242L,
                )

                val response1 = client.nextBlock()
                assertThat(response1).isNotNull()
                assertThat(response1.result).isInstanceOf(RollBackward::class.java)
                assertThat(((response1.result as RollBackward).point as PointDetail).slot).isEqualTo(
                    84242L,
                )

                val response2 = client.nextBlock()
                assertThat(response2).isNotNull()
                assertThat(response2.result).isInstanceOf(RollForward::class.java)
                assertThat(((response2.result as RollForward).block as BlockPraos).slot).isEqualTo(
                    86400L,
                )
            }
        }

    @Test
    fun `test RequestNext allegra block`() =
        runBlocking {
            createChainSyncClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.findIntersect(
                        listOf(
                            PointDetail(
                                slot = 518360L,
                                id = "f9d8b6c77fedd60c3caf5de0ce63a0aeb9d1753269c9c07503d9aa09d5144481",
                            ),
                        ),
                    )
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(IntersectionFoundResult::class.java)
                assertThat((response.result.intersection as PointDetail).slot).isEqualTo(
                    518360L,
                )

                val response1 = client.nextBlock()
                assertThat(response1).isNotNull()
                assertThat(response1.result).isInstanceOf(RollBackward::class.java)
                assertThat(((response1.result as RollBackward).point as PointDetail).slot).isEqualTo(
                    518360L,
                )

                val response2 = client.nextBlock()
                assertThat(response2).isNotNull()
                assertThat(response2.result).isInstanceOf(RollForward::class.java)
                assertThat(((response2.result as RollForward).block as BlockPraos).slot).isEqualTo(
                    518400L,
                )
            }
        }

    @Test
    fun `test RequestNext mary block`() =
        runBlocking {
            createChainSyncClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.findIntersect(
                        listOf(
                            PointDetail(
                                slot = 950340L,
                                id = "74c03af754bcde9cd242c5a168689edcab1756a3f7ae4d5dca1a31d86839c7b1",
                            ),
                        ),
                    )
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(IntersectionFoundResult::class.java)
                assertThat((response.result.intersection as PointDetail).slot).isEqualTo(
                    950340L,
                )

                val response1 = client.nextBlock()
                assertThat(response1).isNotNull()
                assertThat(response1.result).isInstanceOf(RollBackward::class.java)
                assertThat(((response1.result as RollBackward).point as PointDetail).slot).isEqualTo(
                    950340L,
                )

                val response2 = client.nextBlock()
                assertThat(response2).isNotNull()
                assertThat(response2.result).isInstanceOf(RollForward::class.java)
                assertThat(((response2.result as RollForward).block as BlockPraos).slot).isEqualTo(
                    950410L,
                )
            }
        }

    @Test
    fun `test RequestNext alonzo block`() =
        runBlocking {
            createChainSyncClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.findIntersect(
                        listOf(
                            PointDetail(
                                slot = 1382348L,
                                id = "af5fddc7d16a349e1a2af8ba89f4f5d3273955a13095b3709ef6e3db576a0b33",
                            ),
                        ),
                    )
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(IntersectionFoundResult::class.java)
                assertThat((response.result.intersection as PointDetail).slot).isEqualTo(
                    1382348L,
                )

                val response1 = client.nextBlock()
                assertThat(response1).isNotNull()
                assertThat(response1.result).isInstanceOf(RollBackward::class.java)
                assertThat(((response1.result as RollBackward).point as PointDetail).slot).isEqualTo(
                    1382348L,
                )

                val response2 = client.nextBlock()
                assertThat(response2).isNotNull()
                assertThat(response2.result).isInstanceOf(RollForward::class.java)
                assertThat(((response2.result as RollForward).block as BlockPraos).slot).isEqualTo(
                    1382422L,
                )
            }
        }

    @Test
    fun `test RequestNext babbage block`() =
        runBlocking {
            createChainSyncClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.findIntersect(
                        listOf(
                            PointDetail(
                                slot = 17801390L,
                                id = "5334d8f43d03acb9dc0a41af97ba7eb84700773c778012af32493c3300bcf0c3",
                            ),
                        ),
                    )
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(IntersectionFoundResult::class.java)
                assertThat((response.result.intersection as PointDetail).slot).isEqualTo(
                    17801390L,
                )

                val response1 = client.nextBlock()
                assertThat(response1).isNotNull()
                assertThat(response1.result).isInstanceOf(RollBackward::class.java)
                assertThat(((response1.result as RollBackward).point as PointDetail).slot).isEqualTo(
                    17801390L,
                )

                val response2 = client.nextBlock()
                assertThat(response2).isNotNull()
                assertThat(response2.result).isInstanceOf(RollForward::class.java)
                assertThat(((response2.result as RollForward).block as BlockPraos).slot).isEqualTo(
                    17801422L,
                )

                val block = (response2.result as RollForward).block as BlockPraos
                val transaction = block.transactions.firstOrNull {
                    it.id ==
                        "13211d06ab3c63ee1d493fbd35608df4f3bfdf20b70ee0b1552d5aecfdfcce2b"
                }
                assertThat(transaction).isNotNull()
                transaction?.datums?.let { datums ->
                    println("datums: $datums")
                }

                transaction?.redeemers?.let { redeemers ->
                    println("redeemers: $redeemers")
                }
            }
            Unit
        }

    @Test
    fun `test RequestNext conway block`() =
        runBlocking {
            createChainSyncClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.findIntersect(
                        listOf(
                            PointDetail(
                                slot = 68862095L,
                                id = "4494b8ce692720052b1630c57ad0dc113ab161d87702b6398754e70299ead8a2",
                            ),
                        ),
                    )
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(IntersectionFoundResult::class.java)
                assertThat((response.result.intersection as PointDetail).slot).isEqualTo(
                    68862095L,
                )

                val response1 = client.nextBlock()
                assertThat(response1).isNotNull()
                assertThat(response1.result).isInstanceOf(RollBackward::class.java)
                assertThat(((response1.result as RollBackward).point as PointDetail).slot).isEqualTo(
                    68862095L,
                )

                val response2 = client.nextBlock()
                assertThat(response2).isNotNull()
                assertThat(response2.result).isInstanceOf(RollForward::class.java)
                assertThat(((response2.result as RollForward).block as BlockPraos).slot).isEqualTo(
                    68862139L,
                )

                val block = (response2.result as RollForward).block as BlockPraos
                val transaction = block.transactions.firstOrNull {
                    it.id ==
                        "fba6f45218d1797a3eeafd98d5105fc86f335d01dc405634d9918ab002ba8026"
                }
                assertThat(transaction).isNotNull()
                assertThat(block.era).isEqualTo("conway")
            }
            Unit
        }

    @Disabled
    @Test
    fun `test blockchain sync`() =
        runBlocking {
            val root: Logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
            root.level = Level.INFO

            val log = LoggerFactory.getLogger("ChainSync")
            createChainSyncClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
                ogmiosCompact = true,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val intersectResponse =
                    client.findIntersect(
                        listOf(
//                            // preprod
//                            // last byron block
                            PointDetail(
                                slot = 84242L,
                                id = "45899e8002b27df291e09188bfe3aeb5397ac03546a7d0ead93aa2500860f1af",
                            ),
//                    // last alonzo block
//                    PointDetail(
//                        slot = 3542390L,
//                        id = "f93e682d5b91a94d8660e748aef229c19cb285bfb9830db48941d6a78183d81f"
//                    )
                            // plomin hardfork block - mainnet
//                            PointDetail(
//                                slot = 146620747L,
//                                id = "152f9c9e84f00b123f146623cff32faa8b4d7df4284c99322a67574b18074038"
//                            )
                        ),
                    )
//                assertThat(intersectResponse).isNotNull()
//                assertThat(intersectResponse.result).isInstanceOf(IntersectionFoundResult::class.java)
//                assertThat((intersectResponse.result.intersection as PointDetail).slot).isEqualTo(
//                    84242L,
//                )

                var lastLogged = Instant.EPOCH
                var isTip = false
                while (true) {
                    val response =
                        client.nextBlock(
                            timeoutMs =
                                if (isTip) {
                                    // if we're on tip, wait forever for next block to arrive
                                    INFINITE_REQUEST_TIMEOUT_MS
                                } else {
                                    DEFAULT_REQUEST_TIMEOUT_MS
                                },
                        )
                    when (response.result) {
                        is RollBackward -> {
                            log.info("RollBackward: ${response.result.point}")
                        }

                        is RollForward -> {
                            val rollForward = response.result
                            val blockHeight = (rollForward.block as BlockPraos).height
                            val tipBlockHeight = max(blockHeight, rollForward.tip.height)
                            isTip = blockHeight == tipBlockHeight
                            val now = Instant.now()
                            val fiveSecondsAgo = now.minusSeconds(5L)
                            if (isTip || fiveSecondsAgo.isAfter(lastLogged)) {
                                val percent =
                                    floor(blockHeight.toDouble() / tipBlockHeight.toDouble() * 10000.0) / 100.0
                                log.info(
                                    "RollForward: block $blockHeight of $tipBlockHeight: %.2f%% sync'd".format(
                                        percent,
                                    ),
                                )
                                // val blockJsonString = ClientImpl.json.encodeToString(rollForward.block)
                                // log.info("Block: $blockJsonString")
                                lastLogged = now
                            }
                        }
                    }
                }
            }
        }

    @Test
    fun `test metadata deserialize`() =
        runBlocking {
            val metadataJsonString =
                """
                {
                    "hash": "9742d8fbd7c04941c51f87f27948d39ddf30f4507cd4e29eba0df13eaac8edc5",
                    "labels":
                    {
                        "674":
                        {
                            "json": {
                                "map":
                                [
                                    {
                                        "k":
                                        {
                                            "string": "msg"
                                        },
                                        "v":
                                        {
                                            "list":
                                            [
                                                {
                                                    "string": "Minted some nice tokens for you there"
                                                },
                                                {
                                                    "int": 42
                                                }
                                            ]
                                        }
                                    },
                                    {
                                        "k":
                                        {
                                            "bytes": "deadbeefcafebabe"
                                        },
                                        "v":
                                        {
                                            "map":
                                            [
                                                {
                                                    "k":
                                                    {
                                                        "string": "favorite_number"
                                                    },
                                                    "v":
                                                    {
                                                        "int": 79223372036854775807
                                                    }
                                                }
                                            ]
                                        }
                                    }
                                ]
                            }
                        }
                    },
                    "scripts":
                    []
                }
                """.trimIndent()
            val txMetadata: TransactionMetadata = ClientImpl.json.decodeFromString(metadataJsonString)
            println(txMetadata)
        }
}
