package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class TxBodyMary(
    @SerialName("inputs")
    val inputs: List<UtxoInput>,

    @SerialName("outputs")
    val outputs: List<UtxoOutput>,

    @SerialName("certificates")
    val certificates: List<Certificate>,

    @SerialName("withdrawals")
    val withdrawals: Map<String, @Contextual BigInteger>,

    @SerialName("fee")
    @Contextual
    val fee: BigInteger,

    @SerialName("validityInterval")
    val validityInterval: ValidityInterval,

    @SerialName("update")
    val update: ProtocolUpdate?,

    @SerialName("mint")
    val mint: Value,
)
