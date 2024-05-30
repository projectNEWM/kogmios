package io.newm.kogmios.protocols.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
enum class CardanoEra {
    @JsonNames("byron")
    BYRON,

    @JsonNames("shelley")
    SHELLEY,

    @JsonNames("allegra")
    ALLEGRA,

    @JsonNames("mary")
    MARY,

    @JsonNames("alonzo")
    ALONZO,

    @JsonNames("babbage")
    BABBAGE,

    @JsonNames("conway")
    CONWAY,
}
