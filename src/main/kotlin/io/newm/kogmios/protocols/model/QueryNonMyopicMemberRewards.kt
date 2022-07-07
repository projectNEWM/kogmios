package io.newm.kogmios.protocols.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryNonMyopicMemberRewards(
    @SerialName("query")
    val query: NonMyopicMemberRewardsInputs
) : Query()

@Serializable
data class NonMyopicMemberRewardsInputs(
    @SerialName("nonMyopicMemberRewards")
    val nonMyopicMemberRewards: List<NonMyopicMemberRewardsInput>
)
