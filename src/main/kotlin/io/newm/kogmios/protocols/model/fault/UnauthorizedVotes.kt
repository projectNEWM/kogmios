package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.GovernanceVoter
import io.newm.kogmios.protocols.model.UtxoOutputReference

/**
 * The transaction contains votes from unauthorized voters. The field 'data.unauthorizedVotes' indicates the faulty voters and the action they attempted to incorrectly vote for.
 */

@JsonTypeName("3137")
data class UnauthorizedVotesFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: UnauthorizedVotesFaultData,
) : Fault

data class UnauthorizedVotesFaultData(
    @param:JsonProperty(value = "unauthorizedVotes", required = true)
    @get:JsonProperty("unauthorizedVotes")
    val unauthorizedVotes: List<UnauthorizedVote>,
) : FaultData

data class UnauthorizedVote(
    @param:JsonProperty(value = "proposal", required = true)
    @get:JsonProperty("proposal")
    val proposal: UtxoOutputReference,
    @param:JsonProperty(value = "voter", required = true)
    @get:JsonProperty("voter")
    val voter: GovernanceVoter,
)
