package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction is attempting to withdraw rewards from stake credentials that do not engage in on-chain governance. Credentials must be associated with a delegate representative (registered, abstain or noConfidence) before associated rewards can be withdrawn. The field 'data.marginalizedCredentials' lists all the affected credentials.
 */
@Serializable
@SerialName("3150")
data class ForbiddenWithdrawalFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: ForbiddenWithdrawalFaultData,
) : Fault

@Serializable
data class ForbiddenWithdrawalFaultData(
    @SerialName("marginalizedCredentials")
    val marginalizedCredentials: List<String>,
) : FaultData
