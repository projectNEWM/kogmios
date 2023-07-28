package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.GovernanceVoter
import io.newm.kogmios.protocols.model.UtxoOutputReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction contains votes from unauthorized voters. The field 'data.unauthorizedVotes' indicates the faulty voters and the action they attempted to incorrectly vote for.
 */
@Serializable
@SerialName("3137")
data class UnauthorizedVotesFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: UnauthorizedVotesFaultData,
) : Fault

@Serializable
data class UnauthorizedVotesFaultData(
    @SerialName("unauthorizedVotes")
    val unauthorizedVotes: List<UnauthorizedVote>,
) : FaultData

@Serializable
data class UnauthorizedVote(
    @SerialName("proposal")
    val proposal: UtxoOutputReference,
    @SerialName("voter")
    val voter: GovernanceVoter,
)
