package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Happens when attempting to evaluate execution units on a node that isn't enough synchronized.
 */
@Serializable
@SerialName("3003")
data class NodeTipTooOldFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: NodeTipTooOldFaultData,
) : Fault

@Serializable
data class NodeTipTooOldFaultData(
    @SerialName("minimumRequiredEra")
    val minimumRequiredEra: String,
    @SerialName("currentNodeEra")
    val currentNodeEra: String,
) : FaultData
