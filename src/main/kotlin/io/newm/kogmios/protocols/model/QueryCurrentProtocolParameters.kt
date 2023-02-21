package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QueryCurrentProtocolParameters(
    @SerialName("query")
    val query: String = "currentProtocolParameters",
) : Query()
