package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.Const.JSONWSP_RESPONSE
import io.newm.kogmios.protocols.Const.JSONWSP_SERVICENAME
import io.newm.kogmios.protocols.Const.JSONWSP_VERSION
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Base class for all Ogmios responses.
 */
@Serializable
sealed class JsonWspResponse {
    @SerialName("type")
    val type: String = JSONWSP_RESPONSE

    @SerialName("version")
    val version: String = JSONWSP_VERSION

    @SerialName("servicename")
    val servicename: String = JSONWSP_SERVICENAME

    @SerialName("reflection")
    abstract val reflection: String
}
