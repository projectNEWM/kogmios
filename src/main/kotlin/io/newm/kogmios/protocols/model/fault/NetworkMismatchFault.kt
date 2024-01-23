package io.newm.kogmios.protocols.model.fault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Some discriminated entities in the transaction are configured for another network. In fact, payment addresses, stake addresses and stake pool registration certificates are bound to a specific network identifier. This identifier must match the network you're trying to submit them to. Since the Alonzo era, transactions themselves may also contain a network identifier. The field 'data.expectedNetwork' indicates what is the currrently expected network. The field 'data.discriminatedType' indicates what type of entity is causing an issue here. And 'data.invalidEntities' lists all the culprits found in the transaction. The latter isn't present when the transaction's network identifier itself is wrong.
 */
@Serializable
@SerialName("3124")
data class NetworkMismatchFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: NetworkMismatchFaultData,
) : Fault

@Serializable
data class NetworkMismatchFaultData(
    @SerialName("expectedNetwork")
    val expectedNetwork: String,
    @SerialName("discriminatedType")
    val discriminatedType: String,
    @SerialName("invalidEntities")
    val invalidEntities: List<String>? = null,
) : FaultData
