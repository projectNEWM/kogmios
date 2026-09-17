package io.newm.kogmios.protocols.model.result

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import kotlin.time.Instant

data class InstantResult
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    constructor(
        @get:JsonValue val value: Instant,
    ) : OgmiosResult
