package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import org.apache.commons.numbers.fraction.BigFraction

@Serializable
@JsonClassDiscriminator("type")
sealed interface Certificate

@Serializable
@SerialName("stakeDelegation")
data class StakeDelegationCertificate(
    @SerialName("credential")
    val credential: String,
    @SerialName("stakePool")
    val stakePool: StakePool? = null,
    @SerialName("delegateRepresentative")
    val delegateRepresentative: DelegateRepresentative? = null,
) : Certificate

@Serializable
@SerialName("stakeCredentialRegistration")
data class StakeCredentialRegistrationCertificate(
    @SerialName("credential")
    val credential: String,
    @SerialName("deposit")
    val deposit: Ada? = null,
) : Certificate

@Serializable
@SerialName("stakeCredentialDeregistration")
data class StakeCredentialDeregistrationCertificate(
    @SerialName("credential")
    val credential: String,
    @SerialName("deposit")
    val deposit: Ada? = null,
) : Certificate

@Serializable
@SerialName("stakePoolRegistration")
data class StakePoolRegistrationCertificate(
    @SerialName("stakePool")
    val stakePool: StakePoolRegistration,
) : Certificate

@Serializable
data class StakePoolRegistration(
    // bech32 encoding of a pool's verification key
    @SerialName("id")
    val id: String,
    // base16 encoding of a pool's verification key
    @SerialName("vrfVerificationKeyHash")
    val vrfVerificationKeyHash: String,
    @SerialName("owners")
    val owners: List<String>,
    @SerialName("cost")
    val cost: Ada,
    @SerialName("margin")
    @Contextual
    val margin: BigFraction,
    @SerialName("pledge")
    val pledge: Ada,
    @SerialName("rewardAccount")
    val rewardAccount: String,
    @SerialName("metadata")
    val metadata: AnchorMetadata? = null,
    @SerialName("relays")
    val relays: List<RelayResult>,
)

@Serializable
data class AnchorMetadata(
    @SerialName("hash")
    val hash: String,
    @SerialName("url")
    val url: String,
)

@Serializable
@SerialName("stakePoolRetirement")
data class StakePoolRetirementCertificate(
    @SerialName("stakePool")
    val stakePool: PoolRetirement,
) : Certificate

@Serializable
data class PoolRetirement(
    @SerialName("id")
    val id: String,
    @SerialName("retirementEpoch")
    val retirementEpoch: Long,
)

@Serializable
@SerialName("genesisDelegation")
data class GenesisDelegationCertificate(
    @SerialName("delegate")
    val delegate: IdHashWithVrf,
    @SerialName("issuer")
    val issuer: IdHash,
) : Certificate

@Serializable
data class GenesisDelegation(
    @SerialName("delegateKeyHash")
    val delegateKeyHash: String,
    @SerialName("verificationKeyHash")
    val verificationKeyHash: String,
    @SerialName("vrfVerificationKeyHash")
    val vrfVerificationKeyHash: String,
)

@Serializable
@SerialName("constitutionalCommitteeHotKeyRegistration")
data class ConstitutionalCommitteeHotKeyRegistrationCertificate(
    @SerialName("member")
    val member: IdHash,
    @SerialName("hotKey")
    val hotKey: String,
) : Certificate

@Serializable
@SerialName("constitutionalCommitteeRetirement")
data class ConstitutionalCommitteeRetirementCertificate(
    @SerialName("member")
    val member: IdHash,
) : Certificate

@Serializable
@SerialName("delegateRepresentativeRegistration")
data class DelegateRepresentativeRegistrationCertificate(
    @SerialName("delegateRepresentative")
    val delegateRepresentative: DelegateRepresentative,
    @SerialName("deposit")
    val deposit: Ada,
    @SerialName("anchor")
    val anchor: AnchorMetadata? = null,
) : Certificate

@Serializable
@SerialName("delegateRepresentativeUpdate")
data class DelegateRepresentativeUpdateCertificate(
    @SerialName("delegateRepresentative")
    val delegateRepresentative: DelegateRepresentative,
    @SerialName("anchor")
    val anchor: AnchorMetadata? = null,
) : Certificate

@Serializable
@SerialName("delegateRepresentativeRetirement")
data class DelegateRepresentativeRetirementCertificate(
    @SerialName("delegateRepresentative")
    val delegateRepresentative: DelegateRepresentative,
    @SerialName("deposit")
    val deposit: Ada,
) : Certificate
