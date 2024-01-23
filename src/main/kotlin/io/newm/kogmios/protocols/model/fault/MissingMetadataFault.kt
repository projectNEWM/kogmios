package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * No metadata corresponding to a specified metadata hash. It appears that you might have forgotten to attach metadata to a transaction, yet included a hash digest of them in the transaction body? The field 'data.metadata.hash' contains the orphan hash found in the body.
 */
@Serializable
@SerialName("3106")
data class MissingMetadataFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: MissingMetadataFaultData,
) : Fault

@Serializable
data class MissingMetadataFaultData(
    @SerialName("metadata")
    val metadata: MetadataHash,
) : FaultData
