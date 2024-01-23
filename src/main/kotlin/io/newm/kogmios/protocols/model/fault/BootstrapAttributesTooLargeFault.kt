package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.UtxoOutput
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Some output associated with legacy / bootstrap (a.k.a. Byron) addresses have attributes that are too large. The field 'data.bootstrapOutputs' lists all affected outputs.
 */
@Serializable
@SerialName("3126")
data class BootstrapAttributesTooLargeFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: BootstrapAttributesTooLargeFaultData,
) : Fault

@Serializable
data class BootstrapAttributesTooLargeFaultData(
    @SerialName("bootstrapOutputs")
    val bootstrapOutputs: List<UtxoOutput>,
) : FaultData
