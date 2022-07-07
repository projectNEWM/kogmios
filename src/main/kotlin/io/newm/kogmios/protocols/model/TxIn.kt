package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.Serializable

@Serializable
data class TxIn(
    val txId: String,
    val index: Long,
)
