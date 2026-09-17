package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.ScriptPurpose

/**
 * Missing required redeemer(s) for Plutus scripts. There are validators needed for the transaction that do not have an associated redeemer. Redeemer are provided when trying to execute the validation logic of a script (e.g. when spending from an input locked by a script, or minting assets from a Plutus monetary policy. The field 'data.missingRedeemers' lists the different purposes for which a redeemer hasn't been provided.
 */

@JsonTypeName("3109")
data class MissingRedeemersFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: MissingRedeemersFaultData,
) : Fault

data class MissingRedeemersFaultData(
    @param:JsonProperty(value = "missingRedeemers", required = true)
    @get:JsonProperty("missingRedeemers")
    val missingRedeemers: List<ScriptPurpose>,
) : FaultData
