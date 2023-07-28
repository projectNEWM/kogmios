package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParamsChainTip(
    @SerialName("query")
    val query: String = "chainTip",
) : Params()
