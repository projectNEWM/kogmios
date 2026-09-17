package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import io.newm.kogmios.protocols.model.Transaction

data class SubmitTxResult(
    @param:JsonProperty(value = "transaction", required = true)
    @get:JsonProperty("transaction")
    val transaction: Transaction,
) : OgmiosResult
