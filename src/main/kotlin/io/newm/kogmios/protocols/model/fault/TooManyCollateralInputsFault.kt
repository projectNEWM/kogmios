package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * The transaction contains too many collateral inputs. The maximum number of collateral inputs is constrained by a protocol parameter. The field 'data.maximumCollateralInputs' contains the current value of that parameter, and 'data.countedCollateralInputs' indicates how many inputs were actually found in your transaction.
 */

@JsonTypeName("3131")
data class TooManyCollateralInputsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: TooManyCollateralInputsFaultData,
) : Fault

data class TooManyCollateralInputsFaultData(
    @param:JsonProperty(value = "maximumCollateralInputs", required = true)
    @get:JsonProperty("maximumCollateralInputs")
    val maximumCollateralInputs: Long,
    @param:JsonProperty(value = "countedCollateralInputs", required = true)
    @get:JsonProperty("countedCollateralInputs")
    val countedCollateralInputs: Long,
) : FaultData
