package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.EraSummariesResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryLedgerStateEraSummaries message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_LEDGER_STATE_ERA_SUMMARIES)
data class MsgQueryEraSummariesResponse(
    @SerialName("result")
    override val result: EraSummariesResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
