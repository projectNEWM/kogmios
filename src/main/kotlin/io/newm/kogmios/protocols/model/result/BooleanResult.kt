package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class BooleanResult
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    constructor(
        @get:JsonValue val value: Boolean,
    ) : OgmiosResult
