package io.newm.kogmios.protocols.model

import kotlinx.serialization.Serializable

@Serializable
data class EraSummary(
    val start: Bound,
    val end: Bound?,
    val parameters: EraParameters,
)
