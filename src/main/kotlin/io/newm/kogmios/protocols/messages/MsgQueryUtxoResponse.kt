package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.UtxoResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryLedgerStateUtxo message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_LEDGER_STATE_UTXO)
data class MsgQueryUtxoResponse(
    @SerialName("result")
    override val result: UtxoResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
