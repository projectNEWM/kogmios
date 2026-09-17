package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * The transaction is attempting to withdraw rewards from stake credentials that do not engage in on-chain governance. Credentials must be associated with a delegate representative (registered, abstain or noConfidence) before associated rewards can be withdrawn. The field 'data.marginalizedCredentials' lists all the affected credentials.
 */

@JsonTypeName("3150")
data class ForbiddenWithdrawalFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: ForbiddenWithdrawalFaultData,
) : Fault

data class ForbiddenWithdrawalFaultData(
    @param:JsonProperty(value = "marginalizedCredentials", required = true)
    @get:JsonProperty("marginalizedCredentials")
    val marginalizedCredentials: List<String>,
) : FaultData
