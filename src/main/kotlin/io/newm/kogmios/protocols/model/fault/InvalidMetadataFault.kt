package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Invalid metadatum found in transaction metadata. Metadata byte strings must be no longer than 64-bytes and text strings must be no longer than 64 bytes once UTF-8-encoded. Some metadatum in the transaction infringe this rule.
 */
@Serializable
@SerialName("3108")
data class InvalidMetadataFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: FaultData? = null,
) : Fault
