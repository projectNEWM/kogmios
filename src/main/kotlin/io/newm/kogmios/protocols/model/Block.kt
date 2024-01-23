package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("type")
sealed interface Block {
    @SerialName("era")
    val era: String

    @SerialName("id")
    val id: String

    @SerialName("ancestor")
    val ancestor: String

    @SerialName("height")
    val height: Long
}
