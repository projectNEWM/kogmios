package io.newm.kogmios.protocols.model
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
sealed interface Block {
    val era: String

    val id: String

    val ancestor: String

    val height: Long
}
