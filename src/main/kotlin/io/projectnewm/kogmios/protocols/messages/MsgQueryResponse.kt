package io.projectnewm.kogmios.protocols.messages

import io.projectnewm.kogmios.protocols.model.QueryResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a query message is sent.
 */
@Serializable
@SerialName("Query")
data class MsgQueryResponse(
    @SerialName("result")
    val result: QueryResult,
    @SerialName("reflection")
    override val reflection: String,
) : JsonWspResponse()
