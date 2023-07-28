package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Must acquire a mempool snapshot prior to performing any query.
 */
@Serializable
@SerialName("4000")
data class MustAcquireMempoolFirstFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: FaultData? = null,
) : Fault
