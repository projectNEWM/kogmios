package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.Serializable

@Serializable
data class TxIn(
    val txId: String,
    val index: Long,
)
