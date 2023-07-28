package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction failed because it contains datums not associated with any script or output. This could be because you've left some orphan datum behind, because you've listed the wrong inputs in the transaction or because you've just forgotten to include a datum associated with an input. Either way, the field 'data.extraneousDatums' contains a set of data hashes for these extraneous datums.
 */
@Serializable
@SerialName("3112")
data class ExtraneousDatumsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: ExtraneousDatumsFaultData,
) : Fault

@Serializable
data class ExtraneousDatumsFaultData(
    @SerialName("extraneousDatums")
    val extraneousDatums: List<String>,
) : FaultData
