package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import java.math.BigInteger

/**
 * A mempool snapshot has been successfully acquired at a given slot.
 */

data class AcquireMempoolResult(
    @param:JsonProperty(value = "acquired", required = true)
    @get:JsonProperty("acquired")
    val acquired: String,
    @param:JsonProperty(value = "slot", required = true)
    @get:JsonProperty("slot")
    val slot: BigInteger,
) : OgmiosResult
