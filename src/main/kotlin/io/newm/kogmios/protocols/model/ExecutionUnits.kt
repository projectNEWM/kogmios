package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import java.math.BigInteger

data class ExecutionUnits(
    @param:JsonProperty(value = "memory", required = true)
    @get:JsonProperty("memory")
    val memory: BigInteger,
    @param:JsonProperty(value = "cpu", required = true)
    @get:JsonProperty("cpu")
    val cpu: BigInteger,
)
