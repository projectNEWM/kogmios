package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Some discriminated entities in the transaction are configured for another network. In fact, payment addresses, stake addresses and stake pool registration certificates are bound to a specific network identifier. This identifier must match the network you're trying to submit them to. Since the Alonzo era, transactions themselves may also contain a network identifier. The field 'data.expectedNetwork' indicates what is the currrently expected network. The field 'data.discriminatedType' indicates what type of entity is causing an issue here. And 'data.invalidEntities' lists all the culprits found in the transaction. The latter isn't present when the transaction's network identifier itself is wrong.
 */

@JsonTypeName("3124")
data class NetworkMismatchFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: NetworkMismatchFaultData,
) : Fault

data class NetworkMismatchFaultData(
    @param:JsonProperty(value = "expectedNetwork", required = true)
    @get:JsonProperty("expectedNetwork")
    val expectedNetwork: String,
    @param:JsonProperty(value = "discriminatedType", required = true)
    @get:JsonProperty("discriminatedType")
    val discriminatedType: String,
    val invalidEntities: List<String>? = null,
) : FaultData
