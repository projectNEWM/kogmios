package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Some signatures are invalid. Only the serialised transaction *body*, without metadata or witnesses, must be signed.
 */
@Serializable
@SerialName("3100")
data class InvalidSignatoriesFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: InvalidSignatoriesFaultData,
) : Fault

@Serializable
data class InvalidSignatoriesFaultData(
    @SerialName("invalidSignatories")
    val invalidSignatories: List<String>,
) : FaultData
