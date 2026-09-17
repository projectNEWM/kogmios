package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.Script
import io.newm.kogmios.protocols.model.Transaction
import io.newm.kogmios.protocols.model.UtxoOutputValue

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

data class UtxoResultItem(
    @param:JsonProperty(value = "transaction", required = true)
    @get:JsonProperty("transaction")
    val transaction: Transaction,
    @param:JsonProperty(value = "index", required = true)
    @get:JsonProperty("index")
    val index: Int,
    @param:JsonProperty(value = "address", required = true)
    @get:JsonProperty("address")
    val address: String,
    @param:JsonProperty(value = "value", required = true)
    @get:JsonProperty("value")
    val value: UtxoOutputValue,
    val datumHash: String? = null,
    val datum: String? = null,
    val script: Script? = null,
)
