package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QueryPoolIds(
    @SerialName("query")
    val query: String = "poolIds",
) : Query()
