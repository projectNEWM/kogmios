package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryEraStart(
    @SerialName("query")
    val query: String = "eraStart"
) : Query()
