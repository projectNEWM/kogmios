package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Block
import io.newm.kogmios.protocols.model.Tip

/**
 * The next block has been received.
 */

@JsonTypeName("forward")
data class RollForward(
    @param:JsonProperty(value = "tip", required = true)
    @get:JsonProperty("tip")
    val tip: Tip,
    @param:JsonProperty(value = "block", required = true)
    @get:JsonProperty("block")
    val block: Block
) : NextBlockResult
