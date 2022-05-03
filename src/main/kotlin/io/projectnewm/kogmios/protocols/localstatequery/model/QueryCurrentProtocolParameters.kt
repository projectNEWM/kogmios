package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QueryCurrentProtocolParameters(
    @SerialName("query")
    val query: String = "currentProtocolParameters"
) : Query()
