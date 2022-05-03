package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QueryCurrentEpoch(
    @SerialName("query")
    val query: String = "currentEpoch"
) : Query()
