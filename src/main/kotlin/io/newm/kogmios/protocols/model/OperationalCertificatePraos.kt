package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OperationalCertificatePraos(
    @SerialName("count")
    val count: Long,
    @SerialName("kes")
    val kes: Kes,
)
