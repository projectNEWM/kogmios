package io.projectnewm.kogmios.protocols.localchainsync

import com.google.common.truth.Truth.assertThat
import io.projectnewm.kogmios.createLocalChainSyncClient
import io.projectnewm.kogmios.protocols.localstatequery.IntersectionFound
import io.projectnewm.kogmios.protocols.localstatequery.IntersectionNotFound
import io.projectnewm.kogmios.protocols.localstatequery.model.OriginString
import io.projectnewm.kogmios.protocols.localstatequery.model.PointDetail
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

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
                PointDetail(slot = 1598399L, hash = "7e16781b40ebf8b6da18f7b5e8ade855d6738095ef2f1c58c77e88b6e45997a4")
            )
        )
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(IntersectionFound::class.java)
        assertThat(((response.result as IntersectionFound).intersectionFound.point as PointDetail).slot).isEqualTo(
            1598399L
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
}
