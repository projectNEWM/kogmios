package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

data class StringFaultData
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    constructor(
        @get:JsonValue val value: String,
    ) : FaultData
