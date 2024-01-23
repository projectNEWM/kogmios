package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.BytesSize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SizeOfMempoolResult(
    @SerialName("maxCapacity")
    val maxCapacity: BytesSize,
    @SerialName("currentSize")
    val currentSize: BytesSize,
    @SerialName("transactions")
    val transactions: TransactionCount,
) : OgmiosResult

@Serializable
data class TransactionCount(
    @SerialName("count")
    val count: Long,
)
