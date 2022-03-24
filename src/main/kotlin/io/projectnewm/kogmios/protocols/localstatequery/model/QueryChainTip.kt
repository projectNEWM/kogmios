package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class QueryChainTip(
    @SerialName("query")
    val query: String = "chainTip"
) : Query()