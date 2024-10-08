package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConwayConstitutionalCommitteeMember(
    @SerialName("from")
    val from: String,
    @SerialName("id")
    val id: String,
    @SerialName("mandate")
    val mandate: Mandate,
)
