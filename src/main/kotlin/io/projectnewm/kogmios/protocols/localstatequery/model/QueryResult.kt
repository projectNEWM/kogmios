package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.QueryResultSerializer

@kotlinx.serialization.Serializable(with = QueryResultSerializer::class)
interface QueryResult