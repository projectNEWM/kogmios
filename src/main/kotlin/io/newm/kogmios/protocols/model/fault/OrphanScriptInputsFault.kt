package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.UtxoOutputReference

/**
 * Transaction failed because some Plutus scripts are missing their associated datums. 'data.missingDatums' contains a set of data hashes for the missing datums. Ensure all Plutus scripts have an associated datum in the transaction's witness set or, are provided through inline datums in reference inputs.
 */

@JsonTypeName("3114")
data class OrphanScriptInputsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: OrphanScriptInputsFaultData,
) : Fault

data class OrphanScriptInputsFaultData(
    @param:JsonProperty(value = "orphanScriptInputs", required = true)
    @get:JsonProperty("orphanScriptInputs")
    val orphanScriptInputs: List<UtxoOutputReference>,
) : FaultData
