package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.serializers.LongResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = LongResultSerializer::class)
data class LongResult(val value: Long) : OgmiosResult
