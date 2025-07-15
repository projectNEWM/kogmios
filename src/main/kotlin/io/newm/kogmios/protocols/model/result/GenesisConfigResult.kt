package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.Ada
import io.newm.kogmios.protocols.model.AlonzoGenesisProtocolParameters
import io.newm.kogmios.protocols.model.ConwayConstitution
import io.newm.kogmios.protocols.model.ConwayConstitutionalCommittee
import io.newm.kogmios.protocols.model.ConwayGenesisProtocolParameters
import io.newm.kogmios.protocols.model.GenesisDelegate
import io.newm.kogmios.protocols.model.GenesisDelegationConfig
import io.newm.kogmios.protocols.model.Milliseconds
import io.newm.kogmios.protocols.model.ShelleyGenesisProtocolParameters
import io.newm.kogmios.protocols.model.ShelleyGenesisStakePools
import io.newm.kogmios.protocols.model.UpdatableParameters
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import org.apache.commons.numbers.fraction.BigFraction
import java.math.BigInteger
import kotlin.time.Instant

@Serializable
@JsonClassDiscriminator("era")
sealed interface GenesisConfigResult : OgmiosResult

@Serializable
@SerialName("byron")
data class ByronGenesisConfigResult(
    @SerialName("era")
    val era: String,
    @SerialName("genesisKeyHashes")
    val genesisKeyHashes: List<String>,
    @SerialName("genesisDelegations")
    val genesisDelegations: Map<String, GenesisDelegationConfig>,
    @SerialName("startTime")
    val startTime: Instant,
    @SerialName("initialFunds")
    val initialFunds: Map<String, Ada>,
    @SerialName("initialVouchers")
    val initialVouchers: Map<String, Ada>,
    @SerialName("securityParameter")
    @Contextual
    val securityParameter: BigInteger,
    @SerialName("networkMagic")
    val networkMagic: Long,
    @SerialName("updatableParameters")
    val updatableParameters: UpdatableParameters,
) : GenesisConfigResult

@Serializable
@SerialName("shelley")
data class ShelleyGenesisConfigResult(
    @SerialName("era")
    val era: String,
    @SerialName("startTime")
    val startTime: Instant,
    @SerialName("networkMagic")
    val networkMagic: Long,
    @SerialName("network")
    val network: String,
    @SerialName("activeSlotsCoefficient")
    @Contextual
    val activeSlotsCoefficient: BigFraction,
    @SerialName("securityParameter")
    @Contextual
    val securityParameter: BigInteger,
    @SerialName("epochLength")
    val epochLength: Long,
    @SerialName("slotsPerKesPeriod")
    val slotsPerKesPeriod: Long,
    @SerialName("maxKesEvolutions")
    val maxKesEvolutions: Long,
    @SerialName("slotLength")
    val slotLength: Milliseconds,
    @SerialName("updateQuorum")
    val updateQuorum: Long,
    @SerialName("maxLovelaceSupply")
    @Contextual
    val maxLovelaceSupply: BigInteger,
    @SerialName("initialParameters")
    val initialParameters: ShelleyGenesisProtocolParameters,
    @SerialName("initialDelegates")
    val initialDelegates: List<GenesisDelegate>,
    @SerialName("initialFunds")
    val initialFunds: Map<String, Ada>,
    @SerialName("initialStakePools")
    val initialStakePools: ShelleyGenesisStakePools,
) : GenesisConfigResult

@Serializable
@SerialName("alonzo")
data class AlonzoGenesisConfigResult(
    @SerialName("era")
    val era: String,
    @SerialName("updatableParameters")
    val updatableParameters: AlonzoGenesisProtocolParameters,
) : GenesisConfigResult

@Serializable
@SerialName("conway")
data class ConwayGenesisConfigResult(
    @SerialName("era")
    val era: String,
    @SerialName("constitution")
    val constitution: ConwayConstitution,
    @SerialName("constitutionalCommittee")
    val constitutionalCommittee: ConwayConstitutionalCommittee,
    @SerialName("updatableParameters")
    val updatableParameters: ConwayGenesisProtocolParameters,
) : GenesisConfigResult
