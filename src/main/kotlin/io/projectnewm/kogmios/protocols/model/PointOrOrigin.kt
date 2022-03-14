package io.projectnewm.kogmios.protocols.model

import io.projectnewm.kogmios.protocols.Const.ORIGIN

@kotlinx.serialization.Serializable
class PointOrOrigin(
    /**
     * Must be a block hash in hex or the special word "origin"
     */
    val point: String = ORIGIN
)