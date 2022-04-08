package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.LongQueryResultSerializer

@kotlinx.serialization.Serializable(with = LongQueryResultSerializer::class)
class LongQueryResult(val value: Long) : QueryResult
