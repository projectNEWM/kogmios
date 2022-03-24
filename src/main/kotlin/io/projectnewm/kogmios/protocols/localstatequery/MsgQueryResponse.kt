package io.projectnewm.kogmios.protocols.localstatequery

import io.projectnewm.kogmios.protocols.localstatequery.model.QueryResult
import kotlinx.serialization.SerialName

/**
 * Response that comes back from Ogmios after a query message is sent.
 */
@kotlinx.serialization.Serializable
@SerialName("Query")
data class MsgQueryResponse(
    @SerialName("result")
    val result: QueryResult
) : JsonWspResponse()