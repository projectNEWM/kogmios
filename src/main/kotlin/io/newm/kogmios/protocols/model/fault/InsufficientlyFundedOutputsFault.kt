package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Ada
import io.newm.kogmios.protocols.model.UtxoOutput
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Some outputs have an insufficient amount of Ada attached to them. In fact, any new output created in a system must pay for the resources it occupies. Because user-created assets are worthless (from the point of view of the protocol), those resources must be paid in the form of a Ada deposit. The exact depends on the size of the serialized output: the more assets, the higher the amount. The field 'data.insufficientlyFundedOutputs.[].output' contains a list of all transaction outputs that are insufficiently funded. Starting from the Babbage era, the field 'data.insufficientlyFundedOutputs.[].minimumRequiredValue' indicates the required amount of Lovelace (1e6 Lovelace = 1 Ada) needed for each output.
 */
@Serializable
@SerialName("3125")
data class InsufficientlyFundedOutputsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: InsufficientlyFundedOutputsFaultData,
) : Fault

@Serializable
data class InsufficientlyFundedOutputsFaultData(
    @SerialName("insufficientlyFundedOutputs")
    val insufficientlyFundedOutputs: List<InsufficientlyFundedOutput>,
) : FaultData

@Serializable
data class InsufficientlyFundedOutput(
    @SerialName("output")
    val output: UtxoOutput,
    @SerialName("minimumRequiredValue")
    val minimumRequiredValue: Ada,
) : FaultData
