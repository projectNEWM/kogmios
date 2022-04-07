package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class QueryPoolParameters(
    @SerialName("query")
    val query: PoolParameters
) : Query()
