package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryUtxoByTxIn(
    @SerialName("query")
    val query: TxInFilters,
) : Query()

@Serializable
data class TxInFilters(
    @SerialName("utxo")
    val filters: List<TxIn>,
)
