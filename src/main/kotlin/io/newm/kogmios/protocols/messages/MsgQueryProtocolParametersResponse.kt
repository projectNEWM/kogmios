package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.ProtocolParametersResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryLedgerStateProtocolParameters message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_LEDGER_STATE_PROTOCOL_PARAMETERS)
data class MsgQueryProtocolParametersResponse(
    @SerialName("result")
    override val result: ProtocolParametersResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
