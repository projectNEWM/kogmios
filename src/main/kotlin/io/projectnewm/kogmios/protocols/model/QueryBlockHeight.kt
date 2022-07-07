package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class QueryBlockHeight(
    @SerialName("query")
    val query: String = "blockHeight"
) : Query()
