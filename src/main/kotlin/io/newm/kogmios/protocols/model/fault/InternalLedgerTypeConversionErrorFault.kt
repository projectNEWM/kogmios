package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Whoopsie, the ledger failed to upgrade an data-type from an earlier era into data of a newer era. If you ever run into this, please report the issue as you've likely discoverd a critical bug...
 */
@Serializable
@SerialName("3999")
data class InternalLedgerTypeConversionErrorFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: FaultData? = null,
) : Fault
