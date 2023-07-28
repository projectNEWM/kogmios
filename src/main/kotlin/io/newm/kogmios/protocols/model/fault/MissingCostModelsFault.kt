package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * It seems like the transaction is using a Plutus version for which there's no available cost model yet. This could be because that language version is known of the ledger but hasn't yet been enabled through hard-fork. The field 'data.missingCostModels' lists all the languages for which a cost model is missing.
 */
@Serializable
@SerialName("3115")
data class MissingCostModelsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: MissingCostModelsFaultData,
) : Fault

@Serializable
data class MissingCostModelsFaultData(
    @SerialName("missingCostModels")
    val missingCostModels: List<String>,
) : FaultData
