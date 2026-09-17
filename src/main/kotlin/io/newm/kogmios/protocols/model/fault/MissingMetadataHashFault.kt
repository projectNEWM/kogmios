package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Missing required metadata hash in the transaction body.
 * If the transaction includes metadata, then it must also include a hash digest of these serialised metadata in its body to prevent malicious actors from tempering with the data.
 * The field 'data.metadata.hash' contains the expected missing hash digest of the metadata found in the transaction.
 */

@JsonTypeName("3105")
data class MissingMetadataHashFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: MissingMetadataHashFaultData,
) : Fault

data class MissingMetadataHashFaultData(
    @param:JsonProperty(value = "metadata", required = true)
    @get:JsonProperty("metadata")
    val metadata: MetadataHash,
) : FaultData

data class MetadataHash(
    @param:JsonProperty(value = "hash", required = true)
    @get:JsonProperty("hash")
    val hash: String,
)
