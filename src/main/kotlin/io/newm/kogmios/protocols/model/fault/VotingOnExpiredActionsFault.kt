package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction contains votes for an expired proposal. The field 'data.unauthorizedVotes' indicates the faulty voters and the proposal they attempted to vote for.
 */
@Serializable
@SerialName("3160")
data class VotingOnExpiredActionsFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: UnauthorizedVotesFaultData,
) : Fault
