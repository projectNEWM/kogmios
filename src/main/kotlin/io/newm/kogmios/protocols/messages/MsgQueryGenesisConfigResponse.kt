package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.result.GenesisConfigResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a queryNetworkGenesisConfiguration message is sent.
 */
@Serializable
@SerialName(MsgQuery.METHOD_QUERY_NETWORK_GENESIS_CONFIGURATION)
data class MsgQueryGenesisConfigResponse(
    @SerialName("result")
    override val result: GenesisConfigResult,
    @SerialName("id")
    override val id: String,
) : JsonRpcSuccessResponse(result)
