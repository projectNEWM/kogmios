package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction contains an invalid or unauthorized protocol parameters update. This operation is reserved to genesis key holders.
 */
@Serializable
@SerialName("3139")
data class InvalidProtocolParametersUpdateFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: FaultData? = null,
) : Fault
