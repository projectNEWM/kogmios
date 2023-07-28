package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction doesn't provide any collateral inputs but it must. Indeed, when executing scripts, you must provide a collateral amount which is collected by the ledger in case of script execution failure. That collateral serves as a compensation for nodes that aren't thus able to collect normal fees set on the transaction. Note that ledger validations are split in two phases. The first phase regards pretty much every validation outside of script executions. Anything from the first phase doesn't require a collateral and will not consume the collateral in case of failure because they require little computing resources. Besides, in principle, any client application or wallet will prevent you from submitting an invalid transaction to begin with.
 */
@Serializable
@SerialName("3132")
data class MissingCollateralInputsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: FaultData? = null,
) : Fault
