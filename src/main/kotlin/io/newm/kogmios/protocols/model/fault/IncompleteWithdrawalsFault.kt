package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Ada

/**
 * The transaction contains incomplete or invalid rewards withdrawals. When present, rewards withdrawals must consume rewards in full, there cannot be any leftover. The field 'data.incompleteWithdrawals' contains a map of withdrawals and their current rewards balance.
 */

@JsonTypeName("3141")
data class IncompleteWithdrawalsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: IncompleteWithdrawalsFaultData,
) : Fault

data class IncompleteWithdrawalsFaultData(
    @param:JsonProperty(value = "incompleteWithdrawals", required = true)
    @get:JsonProperty("incompleteWithdrawals")
    val incompleteWithdrawals: Map<String, Ada>,
) : FaultData
