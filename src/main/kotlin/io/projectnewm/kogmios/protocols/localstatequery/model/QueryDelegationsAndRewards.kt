package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class QueryDelegationsAndRewards(
    @SerialName("query")
    val query: DelegationsAndRewards
) : Query()
