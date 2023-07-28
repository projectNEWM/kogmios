package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.ScriptPurpose
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Missing required redeemer(s) for Plutus scripts. There are validators needed for the transaction that do not have an associated redeemer. Redeemer are provided when trying to execute the validation logic of a script (e.g. when spending from an input locked by a script, or minting assets from a Plutus monetary policy. The field 'data.missingRedeemers' lists the different purposes for which a redeemer hasn't been provided.
 */
@Serializable
@SerialName("3109")
data class MissingRedeemersFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: MissingRedeemersFaultData,
) : Fault

@Serializable
data class MissingRedeemersFaultData(
    @SerialName("missingRedeemers")
    val missingRedeemers: List<ScriptPurpose>,
) : FaultData
