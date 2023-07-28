package io.newm.kogmios.protocols.model.result

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("direction")
sealed interface NextBlockResult : OgmiosResult
