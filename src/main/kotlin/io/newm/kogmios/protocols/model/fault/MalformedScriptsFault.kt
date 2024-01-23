package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Some Plutus scripts in the witness set or in an output are invalid. Scripts must be well-formed flat-encoded Plutus scripts, CBOR-encoded. Yes, there's a double binary encoding. The outer-most encoding is therefore just a plain CBOR bytestring. Note that some tools such as the cardano-cli triple encode scripts for some reasons, resulting in a double outer-most CBOR encoding. Make sure that your script are correctly encoded. The field 'data.malformedScripts' lists the hash digests of all the problematic scripts.
 */
@Serializable
@SerialName("3116")
data class MalformedScriptsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: MalformedScriptsFaultData,
) : Fault

@Serializable
data class MalformedScriptsFaultData(
    @SerialName("malformedScripts")
    val malformedScripts: List<String>,
) : FaultData
