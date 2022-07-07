package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.Asset
import io.newm.kogmios.serializers.BigIntegerSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AssetSerializer : KSerializer<List<Asset>> {
    private val delegateMapSerializer = MapSerializer(String.serializer(), BigIntegerSerializer)
    override fun deserialize(decoder: Decoder): List<Asset> {
        return delegateMapSerializer.deserialize(decoder).map { (policyAndName, quantity) ->
            val policyAndNameParts = policyAndName.split('.')
            val policyId = policyAndNameParts[0]
            val name = if (policyAndNameParts.size == 2) {
                policyAndNameParts[1]
            } else {
                ""
            }
            Asset(
                policyId = policyId,
                name = name,
                quantity = quantity
            )
        }
    }

    override val descriptor: SerialDescriptor = SerialDescriptor("List<Asset>", delegateMapSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: List<Asset>) {
        delegateMapSerializer.serialize(
            encoder,
            value.associate { asset -> Pair("${asset.policyId}.${asset.name}", asset.quantity) }
        )
    }
}
