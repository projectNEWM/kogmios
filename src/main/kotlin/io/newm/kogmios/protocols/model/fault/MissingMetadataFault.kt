package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * No metadata corresponding to a specified metadata hash. It appears that you might have forgotten to attach metadata to a transaction, yet included a hash digest of them in the transaction body? The field 'data.metadata.hash' contains the orphan hash found in the body.
 */

@JsonTypeName("3106")
data class MissingMetadataFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: MissingMetadataFaultData,
) : Fault

data class MissingMetadataFaultData(
    @param:JsonProperty(value = "metadata", required = true)
    @get:JsonProperty("metadata")
    val metadata: MetadataHash,
) : FaultData
