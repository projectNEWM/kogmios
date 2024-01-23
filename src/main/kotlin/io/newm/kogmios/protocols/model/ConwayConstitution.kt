package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConwayConstitution(
    @SerialName("hash")
    val hash: String? = null,
    @SerialName("anchor")
    val anchor: AnchorMetadata,
)
