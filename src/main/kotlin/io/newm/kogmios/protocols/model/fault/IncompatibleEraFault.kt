package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Returned when trying to evaluate execution units of a pre-Alonzo transaction. Note that this isn't possible with Ogmios because transactions are always de-serialized as Alonzo transactions.
 */

@JsonTypeName("3000")
data class IncompatibleEraFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: IncompatibleEraFaultData,
) : Fault

data class IncompatibleEraFaultData(
    @param:JsonProperty(value = "incompatibleEra", required = true)
    @get:JsonProperty("incompatibleEra")
    val incompatibleEra: String
) : FaultData
