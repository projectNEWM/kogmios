package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Some signatures are missing. A signed transaction must carry signatures for all inputs locked by verification keys or a native script.
 * Transaction may also need signatures for each required extra signatories often required by Plutus Scripts.
 */

@JsonTypeName("3101")
data class MissingSignatoriesFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: MissingSignatoriesFaultData,
) : Fault

data class MissingSignatoriesFaultData(
    @param:JsonProperty(value = "missingSignatories", required = true)
    @get:JsonProperty("missingSignatories")
    val missingSignatories: List<String>,
) : FaultData
