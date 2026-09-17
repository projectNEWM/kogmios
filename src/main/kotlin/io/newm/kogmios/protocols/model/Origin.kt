package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.Const.ORIGIN

data class Origin(
    val point: String = ORIGIN,
) : PointOrOrigin()
