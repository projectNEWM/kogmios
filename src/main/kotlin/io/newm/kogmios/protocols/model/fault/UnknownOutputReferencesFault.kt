package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.UtxoOutputReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction contains unknown UTxO references as inputs. This can happen if the inputs you're trying to spend have already been spent, or if you've simply referred to non-existing UTxO altogether. The field 'data.unknownOutputReferences' indicates all unknown inputs.
 */
@Serializable
@SerialName("3117")
data class UnknownOutputReferencesFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: UnknownOutputReferencesFaultData,
) : Fault

@Serializable
data class UnknownOutputReferencesFaultData(
    @SerialName("unknownOutputReferences")
    val unknownOutputReferences: List<UtxoOutputReference>,
) : FaultData
