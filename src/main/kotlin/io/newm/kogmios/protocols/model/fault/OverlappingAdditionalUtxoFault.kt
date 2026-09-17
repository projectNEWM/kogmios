package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.UtxoOutputReference

/**
 * Happens when providing an additional UTXO set which overlaps with the UTXO on-chain.
 */

@JsonTypeName("3002")
data class OverlappingAdditionalUtxoFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: OverlappingAdditionalUtxoFaultData,
) : Fault

data class OverlappingAdditionalUtxoFaultData(
    @param:JsonProperty(value = "overlappingOutputReferences", required = true)
    @get:JsonProperty("overlappingOutputReferences")
    val overlappingOutputReferences: List<UtxoOutputReference>,
) : FaultData
