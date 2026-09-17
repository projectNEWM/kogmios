package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.BytesSize
import io.newm.kogmios.protocols.model.StakePool

/**
 * Some hash digest of (optional) stake pool metadata is too long. When registering, stake pools can supply an external metadata file and a hash digest of the content. The hashing algorithm is left open but the output digest must be smaller than 32 bytes. The field 'data.infringingStakePool' indicates which stake pool has an invalid metadata hash and 'data.computedMetadataHashSize' documents the computed hash size.
 */

@JsonTypeName("3144")
data class MetadataHashTooLargeFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: MetadataHashTooLargeFaultData,
) : Fault

data class MetadataHashTooLargeFaultData(
    @param:JsonProperty(value = "infringingStakePool", required = true)
    @get:JsonProperty("infringingStakePool")
    val infringingStakePool: StakePool,
    @param:JsonProperty(value = "computedMetadataHashSize", required = true)
    @get:JsonProperty("computedMetadataHashSize")
    val computedMetadataHashSize: BytesSize,
) : FaultData
