package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Missing required metadata hash in the transaction body.
 * If the transaction includes metadata, then it must also include a hash digest of these serialised metadata in its body to prevent malicious actors from tempering with the data.
 * The field 'data.metadata.hash' contains the expected missing hash digest of the metadata found in the transaction.
 */
@Serializable
@SerialName("3105")
data class MissingMetadataHashFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: MissingMetadataHashFaultData,
) : Fault

@Serializable
data class MissingMetadataHashFaultData(
    @SerialName("metadata")
    val metadata: MetadataHash,
) : FaultData

@Serializable
data class MetadataHash(
    @SerialName("hash")
    val hash: String,
)
