package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.Script
import io.newm.kogmios.protocols.model.Transaction
import io.newm.kogmios.protocols.model.UtxoOutputValue
import io.newm.kogmios.protocols.model.serializers.UtxoResultSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = UtxoResultSerializer::class)
class UtxoResult :
    ArrayList<UtxoResultItem>(),
    OgmiosResult {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UtxoResult) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int = super.hashCode()
}

@Serializable
data class UtxoResultItem(
    @SerialName("transaction")
    val transaction: Transaction,
    @SerialName("index")
    val index: Int,
    @SerialName("address")
    val address: String,
    @SerialName("value")
    val value: UtxoOutputValue,
    @SerialName("datumHash")
    val datumHash: String? = null,
    @SerialName("datum")
    val datum: String? = null,
    @SerialName("script")
    val script: Script? = null,
)
