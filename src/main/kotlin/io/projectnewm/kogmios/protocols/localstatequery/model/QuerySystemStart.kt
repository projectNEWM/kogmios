package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QuerySystemStart(
    @SerialName("query")
    val query: String = "systemStart"
) : Query()
