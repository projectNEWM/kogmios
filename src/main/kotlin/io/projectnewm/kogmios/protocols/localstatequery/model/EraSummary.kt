package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.Serializable

@Serializable
data class EraSummary(
    val start: Bound,
    val end: Bound?,
    val parameters: EraParameters,
)
