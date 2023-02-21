package io.newm.kogmios.protocols.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Dummy class to prevent disconnecting the websocket after a fault from Ogmios.
 */
@Serializable
@SerialName("ignore")
class IgnoredJsonWspResponse(
    override val reflection: String = "ignore",
) : JsonWspResponse()
