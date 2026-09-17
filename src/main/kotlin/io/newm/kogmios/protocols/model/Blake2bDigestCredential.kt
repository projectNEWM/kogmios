package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

class Blake2bDigestCredential
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    constructor(
        @get:JsonValue val digest: String,
    ) : ProjectedRewardsInput
