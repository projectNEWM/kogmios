package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * The transaction failed because it contains datums not associated with any script or output. This could be because you've left some orphan datum behind, because you've listed the wrong inputs in the transaction or because you've just forgotten to include a datum associated with an input. Either way, the field 'data.extraneousDatums' contains a set of data hashes for these extraneous datums.
 */

@JsonTypeName("3112")
data class ExtraneousDatumsFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: ExtraneousDatumsFaultData,
) : Fault

data class ExtraneousDatumsFaultData(
    @param:JsonProperty(value = "extraneousDatums", required = true)
    @get:JsonProperty("extraneousDatums")
    val extraneousDatums: List<String>,
) : FaultData
