package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.model.serializers.LongQueryResultSerializer
import kotlinx.serialization.Serializable

@Serializable(with = LongQueryResultSerializer::class)
class LongQueryResult(val value: Long) : QueryResult
