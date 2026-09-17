package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import io.newm.kogmios.protocols.Const

data class OriginString
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    constructor(
        @get:JsonValue val origin: String = Const.ORIGIN,
    ) : PointDetailOrOrigin()
