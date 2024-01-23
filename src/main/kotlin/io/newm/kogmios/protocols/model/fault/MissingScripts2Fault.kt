package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Validator
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An associated script witness is missing. Indeed, any script used in a transaction (when spending, minting, withdrawing or publishing certificates) must be provided in full with the transaction.
 * Scripts must therefore be added either to the witness set or provided as a reference inputs should you use Plutus V2+ and a format from Babbage and beyond.
 */
@Serializable
@SerialName("3011")
data class MissingScripts2Fault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: MissingScripts2FaultData,
) : Fault

@Serializable
data class MissingScripts2FaultData(
    @SerialName("missingScripts")
    val missingScripts: List<Validator>,
) : FaultData
