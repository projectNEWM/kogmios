package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.LongQueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = LongQueryResultSerializer::class)
class LongQueryResult(val value: Long) : QueryResult
