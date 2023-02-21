package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QueryProposedProtocolParameters(
    @SerialName("query")
    val query: String = "proposedProtocolParameters",
) : Query()
