package io.newm.kogmios.protocols.model.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import io.newm.kogmios.protocols.model.Ada
import io.newm.kogmios.protocols.model.Asset
import io.newm.kogmios.protocols.model.Lovelace
import io.newm.kogmios.protocols.model.UtxoOutputValue
import java.math.BigInteger

internal class UtxoOutputValueDeserializer : StdDeserializer<UtxoOutputValue>(UtxoOutputValue::class.java) {
    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): UtxoOutputValue {
        val node = context.readTree(parser)
        val lovelaceNode = node.path("ada").path("lovelace")
        if (lovelaceNode.isMissingNode) {
            return context.reportInputMismatch(UtxoOutputValue::class.java, "ada.lovelace is required")
        }
        val assets =
            node
                .fields()
                .asSequence()
                .filterNot { it.key == "ada" }
                .flatMap { (policyId, quantities) ->
                    quantities.fields().asSequence().map { (name, quantity) ->
                        Asset(policyId, name, context.readTreeAsValue(quantity, BigInteger::class.java))
                    }
                }.toList()
        return UtxoOutputValue(Ada(Lovelace(context.readTreeAsValue(lovelaceNode, BigInteger::class.java))), assets)
    }
}

internal class UtxoOutputValueSerializer : JsonSerializer<UtxoOutputValue>() {
    override fun serialize(
        value: UtxoOutputValue,
        generator: JsonGenerator,
        serializers: SerializerProvider
    ) {
        generator.writeStartObject()
        generator.writeObjectField("ada", value.ada.ada)
        value.assets.orEmpty().groupBy(Asset::policyId).forEach { (policyId, assets) ->
            generator.writeObjectFieldStart(policyId)
            assets.forEach { generator.writeObjectField(it.name, it.quantity) }
            generator.writeEndObject()
        }
        generator.writeEndObject()
    }
}
