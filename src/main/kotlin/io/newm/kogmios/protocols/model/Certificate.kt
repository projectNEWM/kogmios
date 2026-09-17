package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeInfo

import com.fasterxml.jackson.annotation.JsonTypeName

import org.apache.commons.numbers.fraction.BigFraction

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
sealed interface Certificate

@JsonTypeName("stakeDelegation")
data class StakeDelegationCertificate(
    @param:JsonProperty(value = "from", required = true)
    @get:JsonProperty("from")
    val from: String,
    @param:JsonProperty(value = "credential", required = true)
    @get:JsonProperty("credential")
    val credential: String,
    val stakePool: StakePool? = null,
    val delegateRepresentative: DelegateRepresentative? = null,
) : Certificate

@JsonTypeName("stakeCredentialRegistration")
data class StakeCredentialRegistrationCertificate(
    @param:JsonProperty(value = "from", required = true)
    @get:JsonProperty("from")
    val from: String,
    @param:JsonProperty(value = "credential", required = true)
    @get:JsonProperty("credential")
    val credential: String,
    val deposit: Ada? = null,
) : Certificate

@JsonTypeName("stakeCredentialDeregistration")
data class StakeCredentialDeregistrationCertificate(
    @param:JsonProperty(value = "from", required = true)
    @get:JsonProperty("from")
    val from: String,
    @param:JsonProperty(value = "credential", required = true)
    @get:JsonProperty("credential")
    val credential: String,
    val deposit: Ada? = null,
) : Certificate

@JsonTypeName("stakePoolRegistration")
data class StakePoolRegistrationCertificate(
    @param:JsonProperty(value = "stakePool", required = true)
    @get:JsonProperty("stakePool")
    val stakePool: StakePoolRegistration,
) : Certificate

data class StakePoolRegistration(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    @param:JsonProperty(value = "vrfVerificationKeyHash", required = true)
    @get:JsonProperty("vrfVerificationKeyHash")
    val vrfVerificationKeyHash: String,
    @param:JsonProperty(value = "owners", required = true)
    @get:JsonProperty("owners")
    val owners: List<String>,
    @param:JsonProperty(value = "cost", required = true)
    @get:JsonProperty("cost")
    val cost: Ada,
    @param:JsonProperty(value = "margin", required = true)
    @get:JsonProperty("margin")
    val margin: BigFraction,
    @param:JsonProperty(value = "pledge", required = true)
    @get:JsonProperty("pledge")
    val pledge: Ada,
    @param:JsonProperty(value = "rewardAccount", required = true)
    @get:JsonProperty("rewardAccount")
    val rewardAccount: String,
    val metadata: AnchorMetadata? = null,
    @param:JsonProperty(value = "relays", required = true)
    @get:JsonProperty("relays")
    val relays: List<RelayResult>,
)

data class AnchorMetadata(
    @param:JsonProperty(value = "hash", required = true)
    @get:JsonProperty("hash")
    val hash: String,
    @param:JsonProperty(value = "url", required = true)
    @get:JsonProperty("url")
    val url: String,
)

@JsonTypeName("stakePoolRetirement")
data class StakePoolRetirementCertificate(
    @param:JsonProperty(value = "stakePool", required = true)
    @get:JsonProperty("stakePool")
    val stakePool: PoolRetirement,
) : Certificate

data class PoolRetirement(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    @param:JsonProperty(value = "retirementEpoch", required = true)
    @get:JsonProperty("retirementEpoch")
    val retirementEpoch: Long,
)

@JsonTypeName("genesisDelegation")
data class GenesisDelegationCertificate(
    @param:JsonProperty(value = "delegate", required = true)
    @get:JsonProperty("delegate")
    val delegate: IdHashWithVrf,
    @param:JsonProperty(value = "issuer", required = true)
    @get:JsonProperty("issuer")
    val issuer: IdHash,
) : Certificate

data class GenesisDelegation(
    @param:JsonProperty(value = "delegateKeyHash", required = true)
    @get:JsonProperty("delegateKeyHash")
    val delegateKeyHash: String,
    @param:JsonProperty(value = "verificationKeyHash", required = true)
    @get:JsonProperty("verificationKeyHash")
    val verificationKeyHash: String,
    @param:JsonProperty(value = "vrfVerificationKeyHash", required = true)
    @get:JsonProperty("vrfVerificationKeyHash")
    val vrfVerificationKeyHash: String,
)

@JsonTypeName("constitutionalCommitteeDelegation")
data class ConstitutionalCommitteeDelegationCertificate(
    @param:JsonProperty(value = "member", required = true)
    @get:JsonProperty("member")
    val member: IdHash,
    @param:JsonProperty(value = "delegate", required = true)
    @get:JsonProperty("delegate")
    val delegate: ConstitutionalCommitteeDelegate,
) : Certificate

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "status")
sealed interface ConstitutionalCommitteeDelegate

@JsonTypeName("authorized")
data class AuthorizedConstitutionalCommitteeDelegate(
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    val id: String,
    @param:JsonProperty(value = "from", required = true)
    @get:JsonProperty("from")
    val from: String,
) : ConstitutionalCommitteeDelegate

@JsonTypeName("resigned")
data class ResignedConstitutionalCommitteeDelegate(
    val metadata: AnchorMetadata? = null,
) : ConstitutionalCommitteeDelegate

@JsonTypeName("none")
data object NoneConstitutionalCommitteeDelegate : ConstitutionalCommitteeDelegate

@JsonTypeName("constitutionalCommitteeRetirement")
data class ConstitutionalCommitteeRetirementCertificate(
    @param:JsonProperty(value = "member", required = true)
    @get:JsonProperty("member")
    val member: IdHash,
    val metadata: AnchorMetadata? = null,
) : Certificate

@JsonTypeName("delegateRepresentativeRegistration")
data class DelegateRepresentativeRegistrationCertificate(
    @param:JsonProperty(value = "delegateRepresentative", required = true)
    @get:JsonProperty("delegateRepresentative")
    val delegateRepresentative: DelegateRepresentative,
    @param:JsonProperty(value = "deposit", required = true)
    @get:JsonProperty("deposit")
    val deposit: Ada,
    val metadata: AnchorMetadata? = null,
) : Certificate

@JsonTypeName("delegateRepresentativeUpdate")
data class DelegateRepresentativeUpdateCertificate(
    @param:JsonProperty(value = "delegateRepresentative", required = true)
    @get:JsonProperty("delegateRepresentative")
    val delegateRepresentative: DelegateRepresentative,
    val metadata: AnchorMetadata? = null,
) : Certificate

@JsonTypeName("delegateRepresentativeRetirement")
data class DelegateRepresentativeRetirementCertificate(
    @param:JsonProperty(value = "delegateRepresentative", required = true)
    @get:JsonProperty("delegateRepresentative")
    val delegateRepresentative: DelegateRepresentative,
    @param:JsonProperty(value = "deposit", required = true)
    @get:JsonProperty("deposit")
    val deposit: Ada,
) : Certificate
