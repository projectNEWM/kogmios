package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class QueryPoolIds(
    @SerialName("query")
    val query: String = "poolIds"
) : Query()
