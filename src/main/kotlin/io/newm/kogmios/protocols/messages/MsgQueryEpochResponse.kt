package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.LongResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryLedgerStateEpoch message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_LEDGER_STATE_EPOCH)
data class MsgQueryEpochResponse(
    @SerialName("result")
    override val result: LongResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
