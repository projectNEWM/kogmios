package io.projectnewm.kogmios.protocols.localstatequery.model

import io.projectnewm.kogmios.protocols.localstatequery.serializers.QuerySerializer
import kotlinx.serialization.Serializable

@Serializable(with = QuerySerializer::class)
abstract class Query
