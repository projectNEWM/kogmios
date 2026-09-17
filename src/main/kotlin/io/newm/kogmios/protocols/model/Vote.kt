package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

enum class Vote {
    @JsonProperty("yes")
    YES,

    @JsonProperty("no")
    NO,

    @JsonProperty("abstain")
    ABSTAIN,
}
