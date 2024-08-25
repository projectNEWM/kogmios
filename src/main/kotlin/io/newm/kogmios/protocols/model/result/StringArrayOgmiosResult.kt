package io.newm.kogmios.protocols.model.result

import kotlinx.serialization.Serializable

@Serializable
class StringArrayOgmiosResult(
    val value: List<String>
) : OgmiosResult
