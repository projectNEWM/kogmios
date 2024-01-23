package io.newm.kogmios.protocols.model.result

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

/**
 * A mempool snapshot has been successfully acquired at a given slot.
 */
@Serializable
data class AcquireMempoolResult(
    @SerialName("acquired")
    val acquired: String,
    @Contextual
    @SerialName("slot")
    val slot: BigInteger,
) : OgmiosResult
