package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.IgnoredOgmiosResult
import io.newm.kogmios.protocols.model.result.OgmiosResult
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonClassDiscriminator

/**
 * Container for a success response from Ogmios
 */
@Serializable
@JsonClassDiscriminator("method")
sealed class JsonRpcSuccessResponse(
    @Transient
    open val result: OgmiosResult = IgnoredOgmiosResult,
) : JsonRpcResponse()
