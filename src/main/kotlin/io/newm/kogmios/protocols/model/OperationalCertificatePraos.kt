package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

data class OperationalCertificatePraos(
    @param:JsonProperty(value = "count", required = true)
    @get:JsonProperty("count")
    val count: Long,
    @param:JsonProperty(value = "kes", required = true)
    @get:JsonProperty("kes")
    val kes: Kes,
)
