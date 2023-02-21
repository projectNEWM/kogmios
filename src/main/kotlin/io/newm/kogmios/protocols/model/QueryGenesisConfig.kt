package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryGenesisConfig(
    @SerialName("query")
    val query: GenesisConfigType,
) : Query()

@Serializable
data class GenesisConfigType(
    @SerialName("genesisConfig")
    val genesisConfigEra: String, // byron, shelley, alonzo
)
