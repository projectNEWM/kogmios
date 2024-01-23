package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("praos")
data class BlockPraos(
    @SerialName("era")
    override val era: String,
    @SerialName("id")
    override val id: String,
    @SerialName("ancestor")
    override val ancestor: String,
    @SerialName("nonce")
    val nonce: Nonce? = null,
    @SerialName("height")
    override val height: Long,
    @SerialName("slot")
    val slot: Long,
    @SerialName("size")
    val size: BytesSize,
    @SerialName("transactions")
    val transactions: List<Tx>,
    @SerialName("protocol")
    val protocol: ProtocolVersion,
    @SerialName("issuer")
    val issuer: IssuerPraos,
) : Block
