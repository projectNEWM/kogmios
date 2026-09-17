package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Ada
import io.newm.kogmios.protocols.model.UtxoOutput

/**
 * Some outputs have an insufficient amount of Ada attached to them. In fact, any new output created in a system must pay for the resources it occupies. Because user-created assets are worthless (from the point of view of the protocol), those resources must be paid in the form of a Ada deposit. The exact depends on the size of the serialized output: the more assets, the higher the amount. The field 'data.insufficientlyFundedOutputs.[].output' contains a list of all transaction outputs that are insufficiently funded. Starting from the Babbage era, the field 'data.insufficientlyFundedOutputs.[].minimumRequiredValue' indicates the required amount of Lovelace (1e6 Lovelace = 1 Ada) needed for each output.
 */

@JsonTypeName("3125")
data class InsufficientlyFundedOutputsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: InsufficientlyFundedOutputsFaultData,
) : Fault

data class InsufficientlyFundedOutputsFaultData(
    @param:JsonProperty(value = "insufficientlyFundedOutputs", required = true)
    @get:JsonProperty("insufficientlyFundedOutputs")
    val insufficientlyFundedOutputs: List<InsufficientlyFundedOutput>,
) : FaultData

data class InsufficientlyFundedOutput(
    @param:JsonProperty(value = "output", required = true)
    @get:JsonProperty("output")
    val output: UtxoOutput,
    @param:JsonProperty(value = "minimumRequiredValue", required = true)
    @get:JsonProperty("minimumRequiredValue")
    val minimumRequiredValue: Ada,
) : FaultData
