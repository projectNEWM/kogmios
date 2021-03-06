package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.UtxoByTxInQueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = UtxoByTxInQueryResultSerializer::class)
data class UtxoByTxInQueryResult(val value: Map<TxIn, TxOut>) : QueryResult
