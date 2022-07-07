package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class TxBodyAlonzo(
    @SerialName("inputs")
    val inputs: List<UtxoInput>,

    @SerialName("collaterals")
    val collaterals: List<UtxoInput>,

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

    @SerialName("network")
    val network: String?, // mainnet or testnet or null

    @SerialName("scriptIntegrityHash")
    val scriptIntegrityHash: String?,

    @SerialName("requiredExtraSignatures")
    val requiredExtraSignatures: List<String>,
)
