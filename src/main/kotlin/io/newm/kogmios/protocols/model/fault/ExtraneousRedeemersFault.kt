package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.RedeemerPointer

/**
 * Extraneous (non-required) redeemers found in the transaction. There are some redeemers that aren't pointing to any script. This could be because you've left some orphan redeemer behind, because they are pointing at the wrong thing or because you forgot to include their associated validator. Either way, the field 'data.extraneousRedeemers' lists the different orphan redeemer pointers.
 */

@JsonTypeName("3110")
data class ExtraneousRedeemersFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: ExtraneousRedeemersFaultData,
) : Fault

data class ExtraneousRedeemersFaultData(
    @param:JsonProperty(value = "extraneousRedeemers", required = true)
    @get:JsonProperty("extraneousRedeemers")
    val extraneousRedeemers: List<RedeemerPointer>,
) : FaultData
