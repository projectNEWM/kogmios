package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

/**
 * Invalid metadatum found in transaction metadata. Metadata byte strings must be no longer than 64-bytes and text strings must be no longer than 64 bytes once UTF-8-encoded. Some metadatum in the transaction infringe this rule.
 */

@JsonTypeName("3108")
data class InvalidMetadataFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    override val data: FaultData? = null,
) : Fault
