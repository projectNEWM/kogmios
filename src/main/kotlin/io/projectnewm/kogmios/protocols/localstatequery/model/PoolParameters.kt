package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PoolParameters(
    @SerialName("poolParameters")
    val poolParameters: List<String>
)