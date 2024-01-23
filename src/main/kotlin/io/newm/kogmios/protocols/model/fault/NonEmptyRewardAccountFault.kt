package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Ada
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Trying to unregister stake credentials associated to a non empty reward account. You must empty the reward account first (or do it as part of the same transaction) to proceed. The field 'data.nonEmptyRewardAccountBalance' indicates how much Lovelace is left in the account.
 */
@Serializable
@SerialName("3147")
data class NonEmptyRewardAccountFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: NonEmptyRewardAccountFaultData,
) : Fault

@Serializable
data class NonEmptyRewardAccountFaultData(
    @SerialName("nonEmptyRewardAccountBalance")
    val nonEmptyRewardAccountBalance: Ada,
) : FaultData
