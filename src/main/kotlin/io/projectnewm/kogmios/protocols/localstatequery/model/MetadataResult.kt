package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MetadataResult(
    @SerialName("url")
    val url: String,
    @SerialName("hash")
    val hash: String,
)