package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Transaction failed because some Plutus scripts are missing their associated datums. 'data.missingDatums' contains a set of data hashes for the missing datums. Ensure all Plutus scripts have an associated datum in the transaction's witness set or, are provided through inline datums in reference inputs.
 */

@JsonTypeName("3111")
data class MissingDatumsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: MissingDatumsFaultData,
) : Fault

data class MissingDatumsFaultData(
    @param:JsonProperty(value = "missingDatums", required = true)
    @get:JsonProperty("missingDatums")
    val missingDatums: List<String>,
) : FaultData
