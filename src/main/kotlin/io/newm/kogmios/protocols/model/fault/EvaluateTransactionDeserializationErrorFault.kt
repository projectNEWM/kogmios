package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("-32602")
data class EvaluateTransactionDeserializationErrorFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: EvaluateTransactionDeserializationError,
) : Fault

@Serializable
data class EvaluateTransactionDeserializationError(
    val shelley: String,
    val allegra: String,
    val mary: String,
    val alonzo: String,
    val babbage: String,
    val conway: String,
) : FaultData
