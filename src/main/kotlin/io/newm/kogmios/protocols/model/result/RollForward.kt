package io.newm.kogmios.protocols.model.result

import io.newm.kogmios.protocols.model.Block
import io.newm.kogmios.protocols.model.Tip
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The next block has been received.
 */
@Serializable
@SerialName("forward")
data class RollForward(
    @SerialName("tip")
    val tip: Tip,
    @SerialName("block")
    val block: Block
) : NextBlockResult
