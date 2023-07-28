package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("bft")
data class BlockBFT(
    @SerialName("era")
    override val era: String,
    @SerialName("id")
    override val id: String,
    @SerialName("ancestor")
    override val ancestor: String,
    @SerialName("height")
    override val height: Long,
    @SerialName("slot")
    val slot: Long,
    @SerialName("size")
    val size: BytesSize,
    @SerialName("transactions")
    val transactions: List<Tx>,
    @SerialName("operationalCertificates")
    val operationalCertificates: List<OperationalCertificate>,
    @SerialName("protocol")
    val protocol: Protocol,
    @SerialName("issuer")
    val issuer: VerificationKey,
    @SerialName("delegate")
    val delegate: VerificationKey,
) : Block
