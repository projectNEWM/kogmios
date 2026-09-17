package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonAlias

enum class CardanoEra {
    @JsonAlias("byron")
    BYRON,

    @JsonAlias("shelley")
    SHELLEY,

    @JsonAlias("allegra")
    ALLEGRA,

    @JsonAlias("mary")
    MARY,

    @JsonAlias("alonzo")
    ALONZO,

    @JsonAlias("babbage")
    BABBAGE,

    @JsonAlias("conway")
    CONWAY,
}
