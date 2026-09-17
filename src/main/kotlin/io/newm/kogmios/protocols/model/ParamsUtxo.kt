package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

sealed class ParamsUtxo : Params()

data class ParamsUtxoByOutputReferences(
    @param:JsonProperty(value = "outputReferences", required = true)
    @get:JsonProperty("outputReferences")
    val outputReferences: List<UtxoOutputReference>,
) : ParamsUtxo()

data class ParamsUtxoByAddresses(
    @param:JsonProperty(value = "addresses", required = true)
    @get:JsonProperty("addresses")
    val addresses: List<String>,
) : ParamsUtxo()
