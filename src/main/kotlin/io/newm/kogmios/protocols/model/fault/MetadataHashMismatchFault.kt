package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * There's a mismatch between the provided metadata hash digest and the one computed from the actual metadata. The two must match exactly. The field 'data.provided.hash' references the provided hash as found in the transaction body, whereas 'data.computed.hash' contains the one the ledger computed from the actual metadata.
 */
@Serializable
@SerialName("3107")
data class MetadataHashMismatchFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: MetadataHashMismatchFaultData,
) : Fault

@Serializable
data class MetadataHashMismatchFaultData(
    @SerialName("provided")
    val provided: MetadataHash,
    @SerialName("computed")
    val computed: MetadataHash,
) : FaultData
