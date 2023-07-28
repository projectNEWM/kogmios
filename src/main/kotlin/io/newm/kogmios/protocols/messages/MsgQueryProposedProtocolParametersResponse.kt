package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.ProposedProtocolParametersResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryLedgerStateProposedProtocolParameters message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_LEDGER_STATE_PROPOSED_PROTOCOL_PARAMETERS)
data class MsgQueryProposedProtocolParametersResponse(
    @SerialName("result")
    override val result: ProposedProtocolParametersResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
