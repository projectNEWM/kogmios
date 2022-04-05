package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class QueryCurrentEpoch(
    @SerialName("query")
    val query: String = "currentEpoch"
) : Query()