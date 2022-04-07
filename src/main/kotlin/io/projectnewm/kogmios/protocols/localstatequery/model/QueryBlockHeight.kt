package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class QueryBlockHeight(
    @SerialName("query")
    val query: String = "blockHeight"
) : Query()
