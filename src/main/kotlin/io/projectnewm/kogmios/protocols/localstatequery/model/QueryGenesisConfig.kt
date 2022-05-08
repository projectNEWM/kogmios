package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryGenesisConfig(
    @SerialName("query")
    val query: String = "genesisConfig"
) : Query()
