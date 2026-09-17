package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeInfo

import com.fasterxml.jackson.annotation.JsonTypeName

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

import org.apache.commons.numbers.fraction.BigFraction
import java.math.BigInteger
import kotlin.time.Instant

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "era",
    visible = true,
)
sealed interface GenesisConfigResult : OgmiosResult

@JsonTypeName("byron")
data class ByronGenesisConfigResult(
    @param:JsonProperty(value = "era", required = true)
    @get:JsonProperty("era")
    val era: String,
    @param:JsonProperty(value = "genesisKeyHashes", required = true)
    @get:JsonProperty("genesisKeyHashes")
    val genesisKeyHashes: List<String>,
    @param:JsonProperty(value = "genesisDelegations", required = true)
    @get:JsonProperty("genesisDelegations")
    val genesisDelegations: Map<String, GenesisDelegationConfig>,
    @param:JsonProperty(value = "startTime", required = true)
    @get:JsonProperty("startTime")
    val startTime: Instant,
    @param:JsonProperty(value = "initialFunds", required = true)
    @get:JsonProperty("initialFunds")
    val initialFunds: Map<String, Ada>,
    @param:JsonProperty(value = "initialVouchers", required = true)
    @get:JsonProperty("initialVouchers")
    val initialVouchers: Map<String, Ada>,
    @param:JsonProperty(value = "securityParameter", required = true)
    @get:JsonProperty("securityParameter")
    val securityParameter: BigInteger,
    @param:JsonProperty(value = "networkMagic", required = true)
    @get:JsonProperty("networkMagic")
    val networkMagic: Long,
    @param:JsonProperty(value = "updatableParameters", required = true)
    @get:JsonProperty("updatableParameters")
    val updatableParameters: UpdatableParameters,
) : GenesisConfigResult

@JsonTypeName("shelley")
data class ShelleyGenesisConfigResult(
    @param:JsonProperty(value = "era", required = true)
    @get:JsonProperty("era")
    val era: String,
    @param:JsonProperty(value = "startTime", required = true)
    @get:JsonProperty("startTime")
    val startTime: Instant,
    @param:JsonProperty(value = "networkMagic", required = true)
    @get:JsonProperty("networkMagic")
    val networkMagic: Long,
    @param:JsonProperty(value = "network", required = true)
    @get:JsonProperty("network")
    val network: String,
    @param:JsonProperty(value = "activeSlotsCoefficient", required = true)
    @get:JsonProperty("activeSlotsCoefficient")
    val activeSlotsCoefficient: BigFraction,
    @param:JsonProperty(value = "securityParameter", required = true)
    @get:JsonProperty("securityParameter")
    val securityParameter: BigInteger,
    @param:JsonProperty(value = "epochLength", required = true)
    @get:JsonProperty("epochLength")
    val epochLength: Long,
    @param:JsonProperty(value = "slotsPerKesPeriod", required = true)
    @get:JsonProperty("slotsPerKesPeriod")
    val slotsPerKesPeriod: Long,
    @param:JsonProperty(value = "maxKesEvolutions", required = true)
    @get:JsonProperty("maxKesEvolutions")
    val maxKesEvolutions: Long,
    @param:JsonProperty(value = "slotLength", required = true)
    @get:JsonProperty("slotLength")
    val slotLength: Milliseconds,
    @param:JsonProperty(value = "updateQuorum", required = true)
    @get:JsonProperty("updateQuorum")
    val updateQuorum: Long,
    @param:JsonProperty(value = "maxLovelaceSupply", required = true)
    @get:JsonProperty("maxLovelaceSupply")
    val maxLovelaceSupply: BigInteger,
    @param:JsonProperty(value = "initialParameters", required = true)
    @get:JsonProperty("initialParameters")
    val initialParameters: ShelleyGenesisProtocolParameters,
    @param:JsonProperty(value = "initialDelegates", required = true)
    @get:JsonProperty("initialDelegates")
    val initialDelegates: List<GenesisDelegate>,
    @param:JsonProperty(value = "initialFunds", required = true)
    @get:JsonProperty("initialFunds")
    val initialFunds: Map<String, Ada>,
    @param:JsonProperty(value = "initialStakePools", required = true)
    @get:JsonProperty("initialStakePools")
    val initialStakePools: ShelleyGenesisStakePools,
) : GenesisConfigResult

@JsonTypeName("alonzo")
data class AlonzoGenesisConfigResult(
    @param:JsonProperty(value = "era", required = true)
    @get:JsonProperty("era")
    val era: String,
    @param:JsonProperty(value = "updatableParameters", required = true)
    @get:JsonProperty("updatableParameters")
    val updatableParameters: AlonzoGenesisProtocolParameters,
) : GenesisConfigResult

@JsonTypeName("conway")
data class ConwayGenesisConfigResult(
    @param:JsonProperty(value = "era", required = true)
    @get:JsonProperty("era")
    val era: String,
    @param:JsonProperty(value = "constitution", required = true)
    @get:JsonProperty("constitution")
    val constitution: ConwayConstitution,
    @param:JsonProperty(value = "constitutionalCommittee", required = true)
    @get:JsonProperty("constitutionalCommittee")
    val constitutionalCommittee: ConwayConstitutionalCommittee,
    @param:JsonProperty(value = "updatableParameters", required = true)
    @get:JsonProperty("updatableParameters")
    val updatableParameters: ConwayGenesisProtocolParameters,
) : GenesisConfigResult
