package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("code")
sealed interface Fault {
    @SerialName("code")
    val code: Long

    @SerialName("message")
    val message: String

    @SerialName("data")
    val data: FaultData?
}
