package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Some Plutus scripts in the witness set or in an output are invalid. Scripts must be well-formed flat-encoded Plutus scripts, CBOR-encoded. Yes, there's a double binary encoding. The outer-most encoding is therefore just a plain CBOR bytestring. Note that some tools such as the cardano-cli triple encode scripts for some reasons, resulting in a double outer-most CBOR encoding. Make sure that your script are correctly encoded. The field 'data.malformedScripts' lists the hash digests of all the problematic scripts.
 */

@JsonTypeName("3116")
data class MalformedScriptsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: MalformedScriptsFaultData,
) : Fault

data class MalformedScriptsFaultData(
    @param:JsonProperty(value = "malformedScripts", required = true)
    @get:JsonProperty("malformedScripts")
    val malformedScripts: List<String>,
) : FaultData
