package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction is attempting to mint or burn Ada tokens. That is, fortunately, not allowed by the ledger.
 */
@Serializable
@SerialName("3127")
data class MintingOrBurningAdaFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: FaultData? = null,
) : Fault
