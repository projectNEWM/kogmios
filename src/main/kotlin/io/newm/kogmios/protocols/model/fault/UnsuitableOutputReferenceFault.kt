package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.UtxoOutputReference

/**
 *
 */

@JsonTypeName("3013")
data class UnsuitableOutputReferenceFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: UnsuitableOutputReferenceFaultData,
) : Fault

data class UnsuitableOutputReferenceFaultData(
    @param:JsonProperty(value = "outputReference", required = true)
    @get:JsonProperty("outputReference")
    val outputReference: UtxoOutputReference,
) : FaultData
