package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * The transaction contains votes for an expired proposal. The field 'data.unauthorizedVotes' indicates the faulty voters and the proposal they attempted to vote for.
 */

@JsonTypeName("3160")
data class VotingOnExpiredActionsFault(
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
