package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Some script witnesses are missing. Indeed, any script used in a transaction (when spending, minting, withdrawing or publishing certificates) must be provided in full with the transaction.
 * Scripts must therefore be added either to the witness set or provided as a reference inputs should you use Plutus V2+ and a format from Babbage and beyond.
 */

@JsonTypeName("3102")
data class MissingScriptsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: MissingScriptsFaultData,
) : Fault

data class MissingScriptsFaultData(
    @param:JsonProperty(value = "missingScripts", required = true)
    @get:JsonProperty("missingScripts")
    val missingScripts: List<String>,
) : FaultData
