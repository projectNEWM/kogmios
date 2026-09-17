package io.newm.kogmios.protocols.model.result
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "direction")
sealed interface NextBlockResult : OgmiosResult
