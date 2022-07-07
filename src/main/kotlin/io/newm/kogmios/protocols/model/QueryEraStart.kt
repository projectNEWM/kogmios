package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryEraStart(
    @SerialName("query")
    val query: String = "eraStart"
) : Query()
