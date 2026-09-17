package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class LongResult
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    constructor(
        @get:JsonValue val value: Long,
    ) : OgmiosResult
