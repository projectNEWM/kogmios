package io.projectnewm.kogmios.protocols.localstatequery

import io.projectnewm.kogmios.protocols.Const.JSONWSP_REQUEST
import io.projectnewm.kogmios.protocols.Const.JSONWSP_SERVICENAME
import io.projectnewm.kogmios.protocols.Const.JSONWSP_VERSION
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
sealed class JsonWspRequest(
    @SerialName("type")
    val type: String = JSONWSP_REQUEST,
    @SerialName("version")
    val version: String = JSONWSP_VERSION,
    @SerialName("servicename")
    val servicename: String = JSONWSP_SERVICENAME,
)