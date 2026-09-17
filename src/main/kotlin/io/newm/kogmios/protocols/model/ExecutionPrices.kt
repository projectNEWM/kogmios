package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import org.apache.commons.numbers.fraction.BigFraction

data class ExecutionPrices(
    @param:JsonProperty(value = "memory", required = true)
    @get:JsonProperty("memory")
    val memory: BigFraction,
    @param:JsonProperty(value = "cpu", required = true)
    @get:JsonProperty("cpu")
    val cpu: BigFraction,
)
