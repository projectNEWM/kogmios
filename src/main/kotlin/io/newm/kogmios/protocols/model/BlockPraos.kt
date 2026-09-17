package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("praos")
data class BlockPraos(
    @param:JsonProperty(value = "era", required = true)
    @get:JsonProperty("era")
    override val era: String,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
    @param:JsonProperty(value = "ancestor", required = true)
    @get:JsonProperty("ancestor")
    override val ancestor: String,
    val nonce: Nonce? = null,
    @param:JsonProperty(value = "height", required = true)
    @get:JsonProperty("height")
    override val height: Long,
    @param:JsonProperty(value = "slot", required = true)
    @get:JsonProperty("slot")
    val slot: Long,
    @param:JsonProperty(value = "size", required = true)
    @get:JsonProperty("size")
    val size: BytesSize,
    @param:JsonProperty(value = "transactions", required = true)
    @get:JsonProperty("transactions")
    val transactions: List<Tx>,
    @param:JsonProperty(value = "protocol", required = true)
    @get:JsonProperty("protocol")
    val protocol: ProtocolVersion,
    @param:JsonProperty(value = "issuer", required = true)
    @get:JsonProperty("issuer")
    val issuer: IssuerPraos,
) : Block
