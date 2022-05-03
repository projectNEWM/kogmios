package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryPoolParameters(
    @SerialName("query")
    val query: PoolParameters
) : Query()
