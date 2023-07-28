package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.UtxoOutputReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Transaction failed because some Plutus scripts are missing their associated datums. 'data.missingDatums' contains a set of data hashes for the missing datums. Ensure all Plutus scripts have an associated datum in the transaction's witness set or, are provided through inline datums in reference inputs.
 */
@Serializable
@SerialName("3114")
data class OrphanScriptInputsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: OrphanScriptInputsFaultData,
) : Fault

@Serializable
data class OrphanScriptInputsFaultData(
    @SerialName("orphanScriptInputs")
    val orphanScriptInputs: List<UtxoOutputReference>,
) : FaultData
