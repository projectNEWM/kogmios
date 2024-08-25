package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.serializers.BooleanResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = BooleanResultSerializer::class)
data class BooleanResult(
    val value: Boolean
) : OgmiosResult
