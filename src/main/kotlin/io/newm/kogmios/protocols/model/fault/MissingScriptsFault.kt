package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Some script witnesses are missing. Indeed, any script used in a transaction (when spending, minting, withdrawing or publishing certificates) must be provided in full with the transaction.
 * Scripts must therefore be added either to the witness set or provided as a reference inputs should you use Plutus V2+ and a format from Babbage and beyond.
 */
@Serializable
@SerialName("3102")
data class MissingScriptsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: MissingScriptsFaultData,
) : Fault

@Serializable
data class MissingScriptsFaultData(
    @SerialName("missingScripts")
    val missingScripts: List<String>,
) : FaultData
