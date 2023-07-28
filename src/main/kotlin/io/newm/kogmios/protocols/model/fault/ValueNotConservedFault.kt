package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.UtxoOutputValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * In and out value not conserved. The transaction must *exactly* balance: every input must be accounted for. There are various things counting as 'in balance': (a) the total value locked by inputs (or collateral inputs in case of a failing script), (b) rewards coming from withdrawals and (c) return deposits from stake credential or pool de-registration. In a similar fashion, various things count towards the 'out balance': (a) the total value assigned to each transaction output, (b) the fee and (c) any deposit for stake credential or pool registration. The field 'data.valueConsumed' contains the total 'in balance', and 'data.valueProduced' indicates the total amount counting as 'out balance'.
 */
@Serializable
@SerialName("3123")
data class ValueNotConservedFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: ValueNotConservedFaultData,
) : Fault

@Serializable
data class ValueNotConservedFaultData(
    @SerialName("valueConsumed")
    val valueConsumed: UtxoOutputValue,
    @SerialName("valueProduced")
    val valueProduced: UtxoOutputValue,
) : FaultData
