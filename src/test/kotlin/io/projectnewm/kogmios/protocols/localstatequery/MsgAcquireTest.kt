package io.projectnewm.kogmios.protocols.localstatequery

import com.google.common.truth.Truth.assertThat
import io.projectnewm.kogmios.ClientImpl
import io.projectnewm.kogmios.protocols.localstatequery.model.Origin
import io.projectnewm.kogmios.protocols.localstatequery.model.Point
import io.projectnewm.kogmios.protocols.localstatequery.model.PointDetail
import kotlinx.serialization.encodeToString
import org.junit.jupiter.api.Test

class MsgAcquireTest {

    @Test
    fun `test serialize`() {
        val target: JsonWspRequest = MsgAcquire(Origin(), "mirror")
        val jsonString = ClientImpl.json.encodeToString(target)
        assertThat(jsonString).isEqualTo("""{"methodname":"Acquire","type":"jsonwsp/request","version":"1.0","servicename":"ogmios","args":{"point":"origin"},"mirror":"mirror"}""")

        val target3: JsonWspRequest =
            MsgAcquire(
                args = Point(
                    point = PointDetail(
                        1234,
                        "9e871633f7aa356ef11cdcabb6fdd6d8f4b00bc919c57aed71a91af8f86df590"
                    )
                ),
                mirror = "mirror3"
            )
        val jsonString3 = ClientImpl.json.encodeToString(target3)
        assertThat(jsonString3).isEqualTo("""{"methodname":"Acquire","type":"jsonwsp/request","version":"1.0","servicename":"ogmios","args":{"point":{"slot":1234,"hash":"9e871633f7aa356ef11cdcabb6fdd6d8f4b00bc919c57aed71a91af8f86df590"}},"mirror":"mirror3"}""")
    }
}
