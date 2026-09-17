package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.BytesSize

data class SizeOfMempoolResult(
    @param:JsonProperty(value = "maxCapacity", required = true)
    @get:JsonProperty("maxCapacity")
    val maxCapacity: BytesSize,
    @param:JsonProperty(value = "currentSize", required = true)
    @get:JsonProperty("currentSize")
    val currentSize: BytesSize,
    @param:JsonProperty(value = "transactions", required = true)
    @get:JsonProperty("transactions")
    val transactions: TransactionCount,
) : OgmiosResult

data class TransactionCount(
    @param:JsonProperty(value = "count", required = true)
    @get:JsonProperty("count")
    val count: Long,
)
