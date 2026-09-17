package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Ada

/**
 * Trying to unregister stake credentials associated to a non empty reward account. You must empty the reward account first (or do it as part of the same transaction) to proceed. The field 'data.nonEmptyRewardAccountBalance' indicates how much Lovelace is left in the account.
 */

@JsonTypeName("3147")
data class NonEmptyRewardAccountFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: NonEmptyRewardAccountFaultData,
) : Fault

data class NonEmptyRewardAccountFaultData(
    @param:JsonProperty(value = "nonEmptyRewardAccountBalance", required = true)
    @get:JsonProperty("nonEmptyRewardAccountBalance")
    val nonEmptyRewardAccountBalance: Ada,
) : FaultData
