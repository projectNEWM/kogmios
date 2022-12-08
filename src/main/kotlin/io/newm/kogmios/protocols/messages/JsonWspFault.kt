package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.Const.JSONWSP_FAULT
import io.newm.kogmios.protocols.Const.JSONWSP_SERVICENAME
import io.newm.kogmios.protocols.Const.JSONWSP_VERSION
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Container for a fault response from Ogmios
 */
@Serializable
data class JsonWspFault(
    @SerialName("type")
    val type: String = JSONWSP_FAULT,

    @SerialName("version")
    val version: String = JSONWSP_VERSION,

    @SerialName("servicename")
    val servicename: String = JSONWSP_SERVICENAME,

    @SerialName("fault")
    val fault: Fault,

    @SerialName("reflection")
    val reflection: String,
)

@Serializable
data class Fault(
    @SerialName("code")
    val code: String,

    @SerialName("string")
    val string: String,
)
