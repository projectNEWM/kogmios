package io.newm.kogmios.protocols.localchainsync

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.ClientImpl
import io.newm.kogmios.createLocalChainSyncClient
import io.newm.kogmios.protocols.messages.IntersectionFound
import io.newm.kogmios.protocols.messages.IntersectionNotFound
import io.newm.kogmios.protocols.messages.RollBackward
import io.newm.kogmios.protocols.messages.RollForward
import io.newm.kogmios.protocols.model.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import org.junit.Ignore
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.Long.max
import java.time.Instant

class LocalChainSyncTest {

    @Test
    fun `test FindIntersect origin`() = runBlocking {
        val client = createLocalChainSyncClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.findIntersect(
            listOf(
                OriginString()
            )
        )
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
    }

    @Test
    fun `test FindIntersect shelley start`() = runBlocking {
        val client = createLocalChainSyncClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.findIntersect(
            listOf(
                // last byron block on preprod
                PointDetail(
                    slot = 84242L,
                    hash = "45899e8002b27df291e09188bfe3aeb5397ac03546a7d0ead93aa2500860f1af"
                )
                // last byron block on old testnet
                // PointDetail(slot = 1598399L, hash = "7e16781b40ebf8b6da18f7b5e8ade855d6738095ef2f1c58c77e88b6e45997a4")
            )
        )
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
        assertThat(((response.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
            84242L
        )
    }

    @Test
    fun `test FindIntersect not found`() = runBlocking {
        val client = createLocalChainSyncClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.findIntersect(
            listOf(
                PointDetail(
                    slot = 99999999999L,
                    hash = "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
                )
            )
        )
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(IntersectionNotFound::class.java)
        assertThat((response.result as IntersectionNotFound).intersectionNotFound.tip.slot).isGreaterThan(59053932L)
    }

    @Test
    fun `test RequestNext shelley block`() = runBlocking {
        val client = createLocalChainSyncClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.findIntersect(
            listOf(
                PointDetail(slot = 13691478L, hash = "d15be8fccd3dc354fc32b3ddb945471cdd7331717f711bea1bb41ecc157d5d24")
            )
        )
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
        assertThat(((response.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
            13691478L
        )

        val response1 = client.requestNext()
        assertThat(response1).isNotNull()
        assertThat(response1.result).isInstanceOf(RollBackward::class.java)
        assertThat(((response1.result as RollBackward).rollBackward.point as PointDetail).slot).isEqualTo(
            13691478L
        )

        val response2 = client.requestNext()
        assertThat(response2).isNotNull()
        assertThat(response2.result).isInstanceOf(RollForward::class.java)
        assertThat(((response2.result as RollForward).rollForward.block as BlockShelley).shelley.header.slot).isEqualTo(
            13691505L
        )
    }

    @Test
    fun `test RequestNext allegra block`() = runBlocking {
        val client = createLocalChainSyncClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.findIntersect(
            listOf(
                PointDetail(slot = 17416400L, hash = "25d811342cdfade65e77f44b7abdf4936fe6565e1446b1dca5d039dc296898e2")
            )
        )
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
        assertThat(((response.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
            17416400L
        )

        val response1 = client.requestNext()
        assertThat(response1).isNotNull()
        assertThat(response1.result).isInstanceOf(RollBackward::class.java)
        assertThat(((response1.result as RollBackward).rollBackward.point as PointDetail).slot).isEqualTo(
            17416400L
        )

        val response2 = client.requestNext()
        assertThat(response2).isNotNull()
        assertThat(response2.result).isInstanceOf(RollForward::class.java)
        assertThat(((response2.result as RollForward).rollForward.block as BlockAllegra).allegra.header.slot).isEqualTo(
            17416480L
        )
    }

    @Test
    fun `test RequestNext mary block`() = runBlocking {
        val client = createLocalChainSyncClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.findIntersect(
            listOf(
                PointDetail(slot = 25689143L, hash = "cd3c3e17972774f8df24295f5b503187da7855785c8e74b56231f99cff4bdeb6")
            )
        )
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
        assertThat(((response.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
            25689143L
        )

        val response1 = client.requestNext()
        assertThat(response1).isNotNull()
        assertThat(response1.result).isInstanceOf(RollBackward::class.java)
        assertThat(((response1.result as RollBackward).rollBackward.point as PointDetail).slot).isEqualTo(
            25689143L
        )

        val response2 = client.requestNext()
        assertThat(response2).isNotNull()
        assertThat(response2.result).isInstanceOf(RollForward::class.java)
        assertThat(((response2.result as RollForward).rollForward.block as BlockMary).mary.header.slot).isEqualTo(
            25689155L
        )
    }

    @Test
    fun `test RequestNext alonzo block`() = runBlocking {
        val client = createLocalChainSyncClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.findIntersect(
            listOf(
                PointDetail(slot = 58535166L, hash = "93aa78e84806a2a1ab17aa90d7ad844ffe8e08e7863436c36f47fd997bddeba3")
            )
        )
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
        assertThat(((response.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
            58535166L
        )

        val response1 = client.requestNext()
        assertThat(response1).isNotNull()
        assertThat(response1.result).isInstanceOf(RollBackward::class.java)
        assertThat(((response1.result as RollBackward).rollBackward.point as PointDetail).slot).isEqualTo(
            58535166L
        )

        val response2 = client.requestNext()
        assertThat(response2).isNotNull()
        assertThat(response2.result).isInstanceOf(RollForward::class.java)
        assertThat(((response2.result as RollForward).rollForward.block as BlockAlonzo).alonzo.header.slot).isEqualTo(
            58535316L
        )
    }

    @Test
    @Ignore
    fun `test blockchain sync`() = runBlocking {
        val root: Logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        root.level = Level.INFO

        val log = LoggerFactory.getLogger("ChainSync")
        val client = createLocalChainSyncClient(websocketHost = "clockwork", websocketPort = 1337, ogmiosCompact = true)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val intersectResponse = client.findIntersect(
            listOf(
                // preprod
                // last byron block
                PointDetail(slot = 84242L, hash = "45899e8002b27df291e09188bfe3aeb5397ac03546a7d0ead93aa2500860f1af")


                // old testnet
                // PointDetail(slot = 1598399L, hash = "7e16781b40ebf8b6da18f7b5e8ade855d6738095ef2f1c58c77e88b6e45997a4")
                // last mary block
                // PointDetail(slot = 36158304L, hash = "2b95ce628d36c3f8f37a32c2942b48e4f9295ccfe8190bcbc1f012e1e97c79eb")
                // last alonzo block
                // PointDetail(slot = 61625037L, hash = "f4d6cb98b12cd92fd93ebb1b60ae5c3f71f6ff48ede5cfd369c3e1894b5bf585")
            )
        )
        assertThat(intersectResponse).isNotNull()
        assertThat(intersectResponse.result).isInstanceOf(IntersectionFound::class.java)
        assertThat(((intersectResponse.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
            84242L
        )

        var lastLogged = Instant.EPOCH
        while (true) {
            val response = client.requestNext()
            when (response.result) {
                is RollBackward -> {
                    log.info("RollBackward: ${(response.result as RollBackward).rollBackward.point}")
                }

                is RollForward -> {
                    (response.result as RollForward).rollForward.let { rollForward ->
                        val blockHeight = rollForward.block.header.blockHeight
                        val tipBlockHeight = max(blockHeight, rollForward.tip.blockNo)
                        val isTip = blockHeight == tipBlockHeight
                        val now = Instant.now()
                        val tenSecondsAgo = now.minusSeconds(5L)
                        if (isTip || tenSecondsAgo.isAfter(lastLogged)) {
                            val percent = blockHeight.toDouble() / tipBlockHeight.toDouble() * 100.0
                            log.info("RollForward: block $blockHeight of $tipBlockHeight: %.2f%% sync'd".format(percent))
                            lastLogged = now
                        }
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
