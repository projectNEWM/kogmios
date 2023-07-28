package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Returned when trying to evaluate execution units of a pre-Alonzo transaction. Note that this isn't possible with Ogmios because transactions are always de-serialized as Alonzo transactions.
 */
@Serializable
@SerialName("3000")
data class IncompatibleEraFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: IncompatibleEraFaultData,
) : Fault

@Serializable
data class IncompatibleEraFaultData(
    @SerialName("incompatibleEra")
    val incompatibleEra: String
) : FaultData
