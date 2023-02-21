package io.newm.kogmios.protocols.chainsync

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.Client.Companion.DEFAULT_REQUEST_TIMEOUT_MS
import io.newm.kogmios.Client.Companion.INFINITE_REQUEST_TIMEOUT_MS
import io.newm.kogmios.ClientImpl
import io.newm.kogmios.createChainSyncClient
import io.newm.kogmios.protocols.messages.IntersectionFound
import io.newm.kogmios.protocols.messages.IntersectionNotFound
import io.newm.kogmios.protocols.messages.RollBackward
import io.newm.kogmios.protocols.messages.RollForward
import io.newm.kogmios.protocols.model.BlockAllegra
import io.newm.kogmios.protocols.model.BlockAlonzo
import io.newm.kogmios.protocols.model.BlockBabbage
import io.newm.kogmios.protocols.model.BlockMary
import io.newm.kogmios.protocols.model.BlockShelley
import io.newm.kogmios.protocols.model.OriginString
import io.newm.kogmios.protocols.model.PointDetail
import io.newm.kogmios.protocols.model.TxMetadata
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.Long.max
import java.time.Instant

class ChainSyncTest {

    @Test
    fun `test FindIntersect origin`() = runBlocking {
        createChainSyncClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.findIntersect(
                listOf(
                    OriginString(),
                ),
            )
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
        }
    }

    @Test
    fun `test FindIntersect shelley start`() = runBlocking {
        createChainSyncClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.findIntersect(
                listOf(
                    // last byron block on preprod
                    PointDetail(
                        slot = 84242L,
                        hash = "45899e8002b27df291e09188bfe3aeb5397ac03546a7d0ead93aa2500860f1af",
                    ),
                    // last byron block on old testnet
                    // PointDetail(slot = 1598399L, hash = "7e16781b40ebf8b6da18f7b5e8ade855d6738095ef2f1c58c77e88b6e45997a4")
                ),
            )
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
            assertThat(((response.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
                84242L,
            )
        }
    }

    @Test
    fun `test FindIntersect not found`() = runBlocking {
        createChainSyncClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.findIntersect(
                listOf(
                    PointDetail(
                        slot = 99999999999L,
                        hash = "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
                    ),
                ),
            )
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(IntersectionNotFound::class.java)
            assertThat((response.result as IntersectionNotFound).intersectionNotFound.tip.slot).isGreaterThan(14155704L)
        }
    }

    @Test
    fun `test RequestNext shelley block`() = runBlocking {
        createChainSyncClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.findIntersect(
                listOf(
                    PointDetail(
                        slot = 84242L,
                        hash = "45899e8002b27df291e09188bfe3aeb5397ac03546a7d0ead93aa2500860f1af",
                    ),
                ),
            )
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
            assertThat(((response.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
                84242L,
            )

            val response1 = client.requestNext()
            assertThat(response1).isNotNull()
            assertThat(response1.result).isInstanceOf(RollBackward::class.java)
            assertThat(((response1.result as RollBackward).rollBackward.point as PointDetail).slot).isEqualTo(
                84242L,
            )

            val response2 = client.requestNext()
            assertThat(response2).isNotNull()
            assertThat(response2.result).isInstanceOf(RollForward::class.java)
            assertThat(((response2.result as RollForward).rollForward.block as BlockShelley).shelley.header.slot).isEqualTo(
                86400L,
            )
        }
    }

    @Test
    fun `test RequestNext allegra block`() = runBlocking {
        createChainSyncClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.findIntersect(
                listOf(
                    PointDetail(
                        slot = 518360L,
                        hash = "f9d8b6c77fedd60c3caf5de0ce63a0aeb9d1753269c9c07503d9aa09d5144481",
                    ),
                ),
            )
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
            assertThat(((response.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
                518360L,
            )

            val response1 = client.requestNext()
            assertThat(response1).isNotNull()
            assertThat(response1.result).isInstanceOf(RollBackward::class.java)
            assertThat(((response1.result as RollBackward).rollBackward.point as PointDetail).slot).isEqualTo(
                518360L,
            )

            val response2 = client.requestNext()
            assertThat(response2).isNotNull()
            assertThat(response2.result).isInstanceOf(RollForward::class.java)
            assertThat(((response2.result as RollForward).rollForward.block as BlockAllegra).allegra.header.slot).isEqualTo(
                518400L,
            )
        }
    }

    @Test
    fun `test RequestNext mary block`() = runBlocking {
        createChainSyncClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.findIntersect(
                listOf(
                    PointDetail(
                        slot = 950340L,
                        hash = "74c03af754bcde9cd242c5a168689edcab1756a3f7ae4d5dca1a31d86839c7b1",
                    ),
                ),
            )
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
            assertThat(((response.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
                950340L,
            )

            val response1 = client.requestNext()
            assertThat(response1).isNotNull()
            assertThat(response1.result).isInstanceOf(RollBackward::class.java)
            assertThat(((response1.result as RollBackward).rollBackward.point as PointDetail).slot).isEqualTo(
                950340L,
            )

            val response2 = client.requestNext()
            assertThat(response2).isNotNull()
            assertThat(response2.result).isInstanceOf(RollForward::class.java)
            assertThat(((response2.result as RollForward).rollForward.block as BlockMary).mary.header.slot).isEqualTo(
                950410L,
            )
        }
    }

    @Test
    fun `test RequestNext alonzo block`() = runBlocking {
        createChainSyncClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val response = client.findIntersect(
                listOf(
                    PointDetail(
                        slot = 1382348L,
                        hash = "af5fddc7d16a349e1a2af8ba89f4f5d3273955a13095b3709ef6e3db576a0b33",
                    ),
                ),
            )
            assertThat(response).isNotNull()
            assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
            assertThat(((response.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
                1382348L,
            )

            val response1 = client.requestNext()
            assertThat(response1).isNotNull()
            assertThat(response1.result).isInstanceOf(RollBackward::class.java)
            assertThat(((response1.result as RollBackward).rollBackward.point as PointDetail).slot).isEqualTo(
                1382348L,
            )

            val response2 = client.requestNext()
            assertThat(response2).isNotNull()
            assertThat(response2.result).isInstanceOf(RollForward::class.java)
            assertThat(((response2.result as RollForward).rollForward.block as BlockAlonzo).alonzo.header.slot).isEqualTo(
                1382422L,
            )
        }
    }

    @Disabled
    @Test
    fun `test blockchain sync`() = runBlocking {
        val root: Logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        root.level = Level.INFO

        val log = LoggerFactory.getLogger("ChainSync")
        createChainSyncClient(
            websocketHost = "ogmios-kogmios-9ab819.us1.demeter.run",
            websocketPort = 443,
            secure = true,
            ogmiosCompact = true,
        ).use { client ->
            val connectResult = client.connect()
            assertThat(connectResult).isTrue()
            assertThat(client.isConnected).isTrue()

            val intersectResponse = client.findIntersect(
                listOf(
                    // preprod
                    // last byron block
                    PointDetail(
                        slot = 84242L,
                        hash = "45899e8002b27df291e09188bfe3aeb5397ac03546a7d0ead93aa2500860f1af",
                    ),

//                    // last alonzo block
//                    PointDetail(
//                        slot = 3542390L,
//                        hash = "f93e682d5b91a94d8660e748aef229c19cb285bfb9830db48941d6a78183d81f"
//                    )

                ),
            )
            assertThat(intersectResponse).isNotNull()
            assertThat(intersectResponse.result).isInstanceOf(IntersectionFound::class.java)
            assertThat(((intersectResponse.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
                84242L,
            )

            var lastLogged = Instant.EPOCH
            var prevRollForward: RollForward? = null
            var isTip = false
            while (true) {
                val response = client.requestNext(
                    timeoutMs = if (isTip) {
                        // if we're on tip, wait forever for next block to arrive
                        INFINITE_REQUEST_TIMEOUT_MS
                    } else {
                        DEFAULT_REQUEST_TIMEOUT_MS
                    },
                )
                when (response.result) {
                    is RollBackward -> {
                        log.info("RollBackward: ${(response.result as RollBackward).rollBackward.point}")
                    }

                    is RollForward -> {
                        (response.result as RollForward).rollForward.let { rollForward ->
                            val blockHeight = rollForward.block.header.blockHeight
                            val tipBlockHeight = max(blockHeight, rollForward.tip.blockNo)
                            isTip = blockHeight == tipBlockHeight
                            val now = Instant.now()
                            val fiveSecondsAgo = now.minusSeconds(5L)
                            if (isTip || fiveSecondsAgo.isAfter(lastLogged)) {
                                val percent = blockHeight.toDouble() / tipBlockHeight.toDouble() * 100.0
                                log.info(
                                    "RollForward: block $blockHeight of $tipBlockHeight: %.2f%% sync'd".format(
                                        percent,
                                    ),
                                )
                                lastLogged = now
                            }
                            when (rollForward.block) {
                                is BlockAllegra -> {
                                    if (prevRollForward?.rollForward?.block !is BlockAllegra) {
                                        log.warn("Last Shelley - slot: ${prevRollForward?.rollForward?.block?.header?.slot}, hash: ${rollForward.block.header.prevHash}")
                                    }
                                }

                                is BlockMary -> {
                                    if (prevRollForward?.rollForward?.block !is BlockMary) {
                                        log.warn("Last Allegra - slot: ${prevRollForward?.rollForward?.block?.header?.slot}, hash: ${rollForward.block.header.prevHash}")
                                    }
                                }

                                is BlockAlonzo -> {
                                    if (prevRollForward?.rollForward?.block !is BlockAlonzo) {
                                        log.warn("Last Mary - slot: ${prevRollForward?.rollForward?.block?.header?.slot}, hash: ${rollForward.block.header.prevHash}")
                                    }
                                }

                                is BlockBabbage -> {
                                    if (prevRollForward?.rollForward?.block !is BlockBabbage) {
                                        log.warn("Last Alonzo - slot: ${prevRollForward?.rollForward?.block?.header?.slot}, hash: ${rollForward.block.header.prevHash}")
                                    }
                                }

                                else -> {}
                            }

//                        when (rollForward.block) {
//                            is BlockMary -> {
//                                (rollForward.block as BlockMary).mary.body.forEach { txMary ->
//                                    txMary.metadata?.let {
//                                        val m721 = it.body.blob?.get(721.toBigInteger())
//                                        log.warn(m721.toString())
//                                    }
//                                }
//                            }
//
//                            is BlockAlonzo -> {
//                                (rollForward.block as BlockAlonzo).alonzo.body.forEach { txMary ->
//                                    txMary.metadata?.let {
//                                        val m721 = it.body.blob?.get(721.toBigInteger())
//                                        log.warn(m721.toString())
//                                    }
//                                }
//                            }
//
//                            is BlockBabbage -> {
//                                (rollForward.block as BlockBabbage).babbage.body.forEach { txMary ->
//                                    txMary.metadata?.let {
//                                        val m721 = it.body.blob?.get(721.toBigInteger())
//                                        log.warn(m721.toString())
//                                    }
//                                }
//                            }
//
//                            else -> {}
//                        }
                        }
                        prevRollForward = response.result as RollForward
                    }
                }
            }
        }
    }

    @Test
    fun `test metadata deserialize`() = runBlocking {
        val metadataJsonString = """
            {
                "hash": "9742d8fbd7c04941c51f87f27948d39ddf30f4507cd4e29eba0df13eaac8edc5",
                "body":
                {
                    "blob":
                    {
                        "674":
                        {
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
                    },
                    "scripts":
                    []
                }
            }
        """.trimIndent()
        val txMetadata: TxMetadata = ClientImpl.json.decodeFromString(metadataJsonString)
        println(txMetadata)
    }
}
