package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QueryPoolIds(
    @SerialName("query")
    val query: String = "poolIds"
) : Query()
