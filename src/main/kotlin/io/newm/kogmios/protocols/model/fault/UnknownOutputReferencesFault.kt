package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.UtxoOutputReference

/**
 * The transaction contains unknown UTxO references as inputs. This can happen if the inputs you're trying to spend have already been spent, or if you've simply referred to non-existing UTxO altogether. The field 'data.unknownOutputReferences' indicates all unknown inputs.
 */

@JsonTypeName("3117")
data class UnknownOutputReferencesFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: UnknownOutputReferencesFaultData,
) : Fault

data class UnknownOutputReferencesFaultData(
    @param:JsonProperty(value = "unknownOutputReferences", required = true)
    @get:JsonProperty("unknownOutputReferences")
    val unknownOutputReferences: List<UtxoOutputReference>,
) : FaultData
