package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.ExecutionUnits
import io.newm.kogmios.protocols.model.Validator
import io.newm.kogmios.protocols.model.serializers.EvaluateTxResultSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = EvaluateTxResultSerializer::class)
class EvaluateTxResult : ArrayList<EvaluateTx>(), OgmiosResult {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EvaluateTxResult) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

@Serializable
data class EvaluateTx(
    @SerialName("validator")
    val validator: Validator,
    @SerialName("budget")
    val budget: ExecutionUnits,
)
