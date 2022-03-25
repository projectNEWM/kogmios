package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class QueryCurrentProtocolParameters(
    @SerialName("query")
    val query: String = "currentProtocolParameters"
) : Query()