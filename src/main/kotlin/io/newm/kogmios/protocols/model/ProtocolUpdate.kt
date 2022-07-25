package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProtocolUpdate(
    @SerialName("epoch")
    val epoch: Long,

    @SerialName("proposal")
    val proposal: Map<String, UpdateProposal>,
)