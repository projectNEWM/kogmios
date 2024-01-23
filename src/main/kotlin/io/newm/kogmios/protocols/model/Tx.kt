package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class Tx(
    @SerialName("id")
    val id: String,
    @SerialName("spends")
    val spends: String,
    @SerialName("inputs")
    val inputs: List<UtxoInput>,
    @SerialName("references")
    val references: List<UtxoInput>? = null,
    @SerialName("collaterals")
    val collaterals: List<UtxoInput>? = null,
    @SerialName("totalCollateral")
    val totalCollateral: Ada? = null,
    @SerialName("collateralReturn")
    val collateralReturn: UtxoOutput? = null,
    @SerialName("outputs")
    val outputs: List<UtxoOutput>,
    @SerialName("certificates")
    val certificates: List<Certificate>? = null,
    @SerialName("withdrawals")
    val withdrawals: Map<String, Ada>? = null,
    @SerialName("fee")
    val fee: Ada,
    @SerialName("validityInterval")
    val validityInterval: ValidityInterval? = null,
    @SerialName("mint")
    val mint: Map<String, Map<String, @Contextual BigInteger>>? = null,
    @SerialName("network")
    val network: String? = null,
    @SerialName("scriptIntegrityHash")
    val scriptIntegrityHash: String? = null,
    @SerialName("requiredExtraSignatories")
    val requiredExtraSignatories: List<String>? = null,
    @SerialName("requiredExtraScripts")
    val requiredExtraScripts: List<String>? = null,
    @SerialName("proposals")
    val proposals: List<GovernanceProposal>? = null,
    @SerialName("votes")
    val votes: List<GovernanceVote>? = null,
    @SerialName("metadata")
    val metadata: TransactionMetadata? = null,
    @SerialName("signatories")
    val signatories: List<Signatory>,
    @SerialName("scripts")
    val scripts: Map<String, Script>? = null,
    @SerialName("datums")
    val datums: Map<String, String>? = null,
    @SerialName("redeemers")
    val redeemers: List<TxRedeemer>? = null,
    @SerialName("cbor")
    val cbor: String? = null,
)
