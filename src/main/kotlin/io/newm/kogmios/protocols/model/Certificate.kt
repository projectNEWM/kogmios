package io.newm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import java.math.BigDecimal
import java.math.BigInteger

@Serializable(with = CertificateSerializer::class)
sealed class Certificate

@Serializable
data class DelegationCertificate(
    @SerialName("stakeDelegation")
    val stakeDelegation: StakeDelegation,
) : Certificate()

@Serializable
data class StakeDelegation(
    @SerialName("delegator")
    val delegator: String,

    @SerialName("delegatee")
    val delegatee: String,
)

@Serializable
data class StakeKeyRegistrationCertificate(
    @SerialName("stakeKeyRegistration")
    val stakeKeyRegistration: String,
) : Certificate()

@Serializable
data class StakeKeyDeregistrationCertificate(
    @SerialName("stakeKeyDeregistration")
    val stakeKeyDeregistration: String,
) : Certificate()

@Serializable
data class PoolRegistrationCertificate(
    @SerialName("poolRegistration")
    val poolRegistration: PoolRegistration,
) : Certificate()

@Serializable
data class PoolRegistration(
    @SerialName("id")
    val id: String,
    @SerialName("vrf")
    val vrf: String,
    @SerialName("pledge")
    @Contextual
    val pledge: BigInteger,
    @SerialName("cost")
    @Contextual
    val cost: BigInteger,
    @SerialName("margin")
    @Contextual
    val margin: BigDecimal,
    @SerialName("rewardAccount")
    val rewardAccount: String,
    @SerialName("owners")
    val owners: List<String>,
    @SerialName("relays")
    val relays: List<RelayResult>,
    @SerialName("metadata")
    val metadata: PoolRegistrationMetadata?,
)

@Serializable
data class PoolRegistrationMetadata(
    @SerialName("url")
    val url: String,
    @SerialName("hash")
    val hash: String,
)

@Serializable
data class PoolRetirementCertificate(
    @SerialName("poolRetirement")
    val poolRetirement: PoolRetirement,
) : Certificate()

@Serializable
data class PoolRetirement(
    @SerialName("poolId")
    val poolId: String,
    @SerialName("retirementEpoch")
    val retirementEpoch: Long,
)

@Serializable
data class GenesisDelegationCertificate(
    @SerialName("genesisDelegation")
    val genesisDelegation: GenesisDelegation,
) : Certificate()

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
data class MoveInstantaneousRewardsCertificate(
    @SerialName("moveInstantaneousRewards")
    val moveInstantaneousRewards: MoveInstantaneousRewards,
) : Certificate()

@Serializable
data class MoveInstantaneousRewards(
    @SerialName("rewards")
    val rewards: Map<String, @Contextual BigInteger>? = null,
    @SerialName("value")
    @Contextual
    val value: BigInteger? = null,
    @SerialName("pot")
    val pot: String, // reserves or treasury
)

object CertificateSerializer : JsonContentPolymorphicSerializer<Certificate>(Certificate::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Certificate> {
        return if ("stakeDelegation" in element.jsonObject) {
            DelegationCertificate.serializer()
        } else if ("stakeKeyRegistration" in element.jsonObject) {
            StakeKeyRegistrationCertificate.serializer()
        } else if ("stakeKeyDeregistration" in element.jsonObject) {
            StakeKeyDeregistrationCertificate.serializer()
        } else if ("poolRegistration" in element.jsonObject) {
            PoolRegistrationCertificate.serializer()
        } else if ("poolRetirement" in element.jsonObject) {
            PoolRetirementCertificate.serializer()
        } else if ("genesisDelegation" in element.jsonObject) {
            GenesisDelegationCertificate.serializer()
        } else if ("moveInstantaneousRewards" in element.jsonObject) {
            MoveInstantaneousRewardsCertificate.serializer()
        } else {
            throw IllegalStateException("No serializer found!: $element")
        }
    }
}
