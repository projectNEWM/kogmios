package io.newm.kogmios.protocols.model

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("ebb")
data class BlockEBB(
    @param:JsonProperty(value = "era", required = true)
    @get:JsonProperty("era")
    override val era: String,
    @param:JsonProperty(value = "id", required = true)
    @get:JsonProperty("id")
    override val id: String,
    @param:JsonProperty(value = "ancestor", required = true)
    @get:JsonProperty("ancestor")
    override val ancestor: String,
    @param:JsonProperty(value = "height", required = true)
    @get:JsonProperty("height")
    override val height: Long,
) : Block
