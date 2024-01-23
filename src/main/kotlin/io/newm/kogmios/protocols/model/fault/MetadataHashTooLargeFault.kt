package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.BytesSize
import io.newm.kogmios.protocols.model.StakePool
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Some hash digest of (optional) stake pool metadata is too long. When registering, stake pools can supply an external metadata file and a hash digest of the content. The hashing algorithm is left open but the output digest must be smaller than 32 bytes. The field 'data.infringingStakePool' indicates which stake pool has an invalid metadata hash and 'data.computedMetadataHashSize' documents the computed hash size.
 */
@Serializable
@SerialName("3144")
data class MetadataHashTooLargeFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: MetadataHashTooLargeFaultData,
) : Fault

@Serializable
data class MetadataHashTooLargeFaultData(
    @SerialName("infringingStakePool")
    val infringingStakePool: StakePool,
    @SerialName("computedMetadataHashSize")
    val computedMetadataHashSize: BytesSize,
) : FaultData
