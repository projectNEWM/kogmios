package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.InstantQueryResultSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable(with = InstantQueryResultSerializer::class)
class InstantQueryResult(val value: Instant) : QueryResult
