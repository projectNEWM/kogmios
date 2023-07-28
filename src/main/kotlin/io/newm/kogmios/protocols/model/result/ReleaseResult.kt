package io.newm.kogmios.protocols.model.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseResult(
    @SerialName("released")
    val released: String,
) : OgmiosResult
