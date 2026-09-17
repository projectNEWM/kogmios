package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.math.BigInteger

data class Tx(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    @param:JsonProperty(value = "spends", required = true)
    @get:JsonProperty("spends")
    val spends: String,
    @param:JsonProperty(value = "inputs", required = true)
    @get:JsonProperty("inputs")
    val inputs: List<UtxoInput>,
    val references: List<UtxoInput>? = null,
    val collaterals: List<UtxoInput>? = null,
    val totalCollateral: Ada? = null,
    val collateralReturn: UtxoOutput? = null,
    @param:JsonProperty(value = "outputs", required = true)
    @get:JsonProperty("outputs")
    val outputs: List<UtxoOutput>,
    val certificates: List<Certificate>? = null,
    val withdrawals: Map<String, Ada>? = null,
    @param:JsonProperty(value = "fee", required = true)
    @get:JsonProperty("fee")
    val fee: Ada,
    val validityInterval: ValidityInterval? = null,
    val mint: Map<String, Map<String, BigInteger>>? = null,
    val network: String? = null,
    val scriptIntegrityHash: String? = null,
    val requiredExtraSignatories: List<String>? = null,
    val requiredExtraScripts: List<String>? = null,
    val proposals: List<GovernanceProposal>? = null,
    val votes: List<GovernanceVote>? = null,
    val metadata: TransactionMetadata? = null,
    @param:JsonProperty(value = "signatories", required = true)
    @get:JsonProperty("signatories")
    val signatories: List<Signatory>,
    val scripts: Map<String, Script>? = null,
    val datums: Map<String, String>? = null,
    val redeemers: List<TxRedeemer>? = null,
    val cbor: String? = null,
)
