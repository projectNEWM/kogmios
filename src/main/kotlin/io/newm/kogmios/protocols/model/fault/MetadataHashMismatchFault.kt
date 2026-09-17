package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * There's a mismatch between the provided metadata hash digest and the one computed from the actual metadata. The two must match exactly. The field 'data.provided.hash' references the provided hash as found in the transaction body, whereas 'data.computed.hash' contains the one the ledger computed from the actual metadata.
 */

@JsonTypeName("3107")
data class MetadataHashMismatchFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: MetadataHashMismatchFaultData,
) : Fault

data class MetadataHashMismatchFaultData(
    @param:JsonProperty(value = "provided", required = true)
    @get:JsonProperty("provided")
    val provided: MetadataHash,
    @param:JsonProperty(value = "computed", required = true)
    @get:JsonProperty("computed")
    val computed: MetadataHash,
) : FaultData
