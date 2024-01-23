package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Unrecognized certificate type. This error is a placeholder due to how internal data-types are modeled. If you ever run into this, please report the issue as you've likely discoverd a critical bug...
 */
@Serializable
@SerialName("3998")
data class UnrecognizedCertificateTypeFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: FaultData? = null,
) : Fault
