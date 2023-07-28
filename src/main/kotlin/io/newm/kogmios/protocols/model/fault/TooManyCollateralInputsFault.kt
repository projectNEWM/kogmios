package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction contains too many collateral inputs. The maximum number of collateral inputs is constrained by a protocol parameter. The field 'data.maximumCollateralInputs' contains the current value of that parameter, and 'data.countedCollateralInputs' indicates how many inputs were actually found in your transaction.
 */
@Serializable
@SerialName("3131")
data class TooManyCollateralInputsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: TooManyCollateralInputsFaultData,
) : Fault

@Serializable
data class TooManyCollateralInputsFaultData(
    @SerialName("maximumCollateralInputs")
    val maximumCollateralInputs: Long,
    @SerialName("countedCollateralInputs")
    val countedCollateralInputs: Long,
) : FaultData
