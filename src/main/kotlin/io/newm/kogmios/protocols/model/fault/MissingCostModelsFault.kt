package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * It seems like the transaction is using a Plutus version for which there's no available cost model yet. This could be because that language version is known of the ledger but hasn't yet been enabled through hard-fork. The field 'data.missingCostModels' lists all the languages for which a cost model is missing.
 */

@JsonTypeName("3115")
data class MissingCostModelsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: MissingCostModelsFaultData,
) : Fault

data class MissingCostModelsFaultData(
    @param:JsonProperty(value = "missingCostModels", required = true)
    @get:JsonProperty("missingCostModels")
    val missingCostModels: List<String>,
) : FaultData
