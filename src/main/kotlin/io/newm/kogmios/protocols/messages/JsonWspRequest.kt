package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.Const.JSONWSP_REQUEST
import io.newm.kogmios.protocols.Const.JSONWSP_SERVICENAME
import io.newm.kogmios.protocols.Const.JSONWSP_VERSION
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class JsonWspRequest {
    @SerialName("type")
    val type: String = JSONWSP_REQUEST

    @SerialName("version")
    val version: String = JSONWSP_VERSION

    @SerialName("servicename")
    val servicename: String = JSONWSP_SERVICENAME

    @SerialName("mirror")
    abstract val mirror: String
}
