package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.QuerySerializer

@kotlinx.serialization.Serializable(with = QuerySerializer::class)
abstract class Query
