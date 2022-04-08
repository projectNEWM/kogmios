package io.projectnewm.kogmios.protocols.localstatequery

import io.projectnewm.kogmios.protocols.Const.JSONWSP_RESPONSE
import io.projectnewm.kogmios.protocols.Const.JSONWSP_SERVICENAME
import io.projectnewm.kogmios.protocols.Const.JSONWSP_VERSION
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
sealed class JsonWspResponse(
    @SerialName("type")
    val type: String = JSONWSP_RESPONSE,
    @SerialName("version")
    val version: String = JSONWSP_VERSION,
    @SerialName("servicename")
    val servicename: String = JSONWSP_SERVICENAME,
    @SerialName("reflection")
    val reflection: String = "dummy",
)
