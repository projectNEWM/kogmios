package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class QueryEraStart(
    @SerialName("query")
    val query: String = "eraStart"
) : Query()
