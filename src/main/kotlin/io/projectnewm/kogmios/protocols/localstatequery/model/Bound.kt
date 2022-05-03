package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Bound(
    @SerialName("time")
    val time: Long,
    @SerialName("slot")
    val slot: Long,
    @SerialName("epoch")
    val epoch: Long,
) : QueryResult
