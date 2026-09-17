package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.ExecutionUnits
import io.newm.kogmios.protocols.model.Validator

class EvaluateTxResult :
    ArrayList<EvaluateTx>(),
    OgmiosResult {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EvaluateTxResult) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int = super.hashCode()
}

data class EvaluateTx(
    @param:JsonProperty(value = "validator", required = true)
    @get:JsonProperty("validator")
    val validator: Validator,
    @param:JsonProperty(value = "budget", required = true)
    @get:JsonProperty("budget")
    val budget: ExecutionUnits,
)
