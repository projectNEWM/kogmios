package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Transaction failed because some Plutus scripts are missing their associated datums. 'data.missingDatums' contains a set of data hashes for the missing datums. Ensure all Plutus scripts have an associated datum in the transaction's witness set or, are provided through inline datums in reference inputs.
 */
@Serializable
@SerialName("3111")
data class MissingDatumsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: MissingDatumsFaultData,
) : Fault

@Serializable
data class MissingDatumsFaultData(
    @SerialName("missingDatums")
    val missingDatums: List<String>,
) : FaultData
