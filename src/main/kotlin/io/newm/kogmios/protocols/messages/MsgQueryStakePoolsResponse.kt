package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.StakePoolsResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryLedgerStateStakePools message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_LEDGER_STATE_STAKE_POOLS)
data class MsgQueryStakePoolsResponse(
    @SerialName("result")
    override val result: StakePoolsResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
