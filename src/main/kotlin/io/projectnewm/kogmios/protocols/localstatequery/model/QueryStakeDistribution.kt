package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QueryStakeDistribution(
    @SerialName("query")
    val query: String = "stakeDistribution"
) : Query()
