package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Tip
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * No intersection found with the requested points.
 */
@Serializable
@SerialName("1000")
data class IntersectionNotFoundFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: IntersectionNotFoundFaultData,
) : Fault

@Serializable
data class IntersectionNotFoundFaultData(
    @SerialName("tip")
    val tip: Tip,
) : FaultData
