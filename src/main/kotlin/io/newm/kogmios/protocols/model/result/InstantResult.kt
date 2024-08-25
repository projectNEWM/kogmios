package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.serializers.InstantResultSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable(with = InstantResultSerializer::class)
data class InstantResult(
    val value: Instant
) : OgmiosResult
