package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.UtxoByTxInQueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = UtxoByTxInQueryResultSerializer::class)
data class UtxoByTxInQueryResult(val value: Map<TxIn, TxOut>) : QueryResult
