package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.result.Bound
import kotlinx.serialization.Serializable

@Serializable
data class EraSummary(
    val start: Bound,
    val end: Bound?,
    val parameters: EraParameters,
)
