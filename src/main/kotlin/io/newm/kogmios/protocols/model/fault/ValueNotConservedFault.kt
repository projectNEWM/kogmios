package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.UtxoOutputValue

/**
 * In and out value not conserved. The transaction must *exactly* balance: every input must be accounted for. There are various things counting as 'in balance': (a) the total value locked by inputs (or collateral inputs in case of a failing script), (b) rewards coming from withdrawals and (c) return deposits from stake credential or pool de-registration. In a similar fashion, various things count towards the 'out balance': (a) the total value assigned to each transaction output, (b) the fee and (c) any deposit for stake credential or pool registration. The field 'data.valueConsumed' contains the total 'in balance', and 'data.valueProduced' indicates the total amount counting as 'out balance'.
 */

@JsonTypeName("3123")
data class ValueNotConservedFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: ValueNotConservedFaultData,
) : Fault

data class ValueNotConservedFaultData(
    @param:JsonProperty(value = "valueConsumed", required = true)
    @get:JsonProperty("valueConsumed")
    val valueConsumed: UtxoOutputValue,
    @param:JsonProperty(value = "valueProduced", required = true)
    @get:JsonProperty("valueProduced")
    val valueProduced: UtxoOutputValue,
) : FaultData
