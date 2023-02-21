package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryEraSummaries(
    @SerialName("query")
    val query: String = "eraSummaries",
) : Query()
