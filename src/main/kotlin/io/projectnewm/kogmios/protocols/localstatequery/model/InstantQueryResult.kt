package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.InstantQueryResultSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable(with = InstantQueryResultSerializer::class)
class InstantQueryResult(val value: Instant) : QueryResult
