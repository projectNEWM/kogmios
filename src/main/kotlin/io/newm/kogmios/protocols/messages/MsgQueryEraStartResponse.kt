package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.Bound
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryLedgerStateEraStart message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_LEDGER_STATE_ERA_START)
data class MsgQueryEraStartResponse(
    @SerialName("result")
    override val result: Bound,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
