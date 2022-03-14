package io.projectnewm.kogmios.protocols.localstatequery

import com.google.common.truth.Truth.assertThat
import io.projectnewm.kogmios.Client
import io.projectnewm.kogmios.protocols.model.PointOrOrigin
import kotlinx.serialization.encodeToString
import org.junit.jupiter.api.Test

class MsgAcquireTest {

    @Test
    fun `test serialize`() {
        val target: JsonWspRequest = MsgAcquire()
        val jsonString = Client.json.encodeToString(target)
        assertThat(jsonString).isEqualTo("""{"methodname":"Acquire","type":"jsonwsp/request","version":"1.0","servicename":"ogmios"}""")

        val target1: JsonWspRequest = MsgAcquire(args = PointOrOrigin(point = "origin"))
        val jsonString1 = Client.json.encodeToString(target1)
        assertThat(jsonString1).isEqualTo("""{"methodname":"Acquire","type":"jsonwsp/request","version":"1.0","servicename":"ogmios","args":{"point":"origin"}}""")

        val target3: JsonWspRequest =
            MsgAcquire(args = PointOrOrigin(point = "9e871633f7aa356ef11cdcabb6fdd6d8f4b00bc919c57aed71a91af8f86df590"))
        val jsonString3 = Client.json.encodeToString(target3)
        assertThat(jsonString3).isEqualTo("""{"methodname":"Acquire","type":"jsonwsp/request","version":"1.0","servicename":"ogmios","args":{"point":"9e871633f7aa356ef11cdcabb6fdd6d8f4b00bc919c57aed71a91af8f86df590"}}""")
    }
}