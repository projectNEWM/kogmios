package io.newm.kogmios.protocols.model.serializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import io.newm.kogmios.protocols.model.OriginString
import io.newm.kogmios.protocols.model.PointDetail
import io.newm.kogmios.protocols.model.PointDetailOrOrigin

internal class PointDetailOrOriginDeserializer : StdDeserializer<PointDetailOrOrigin>(PointDetailOrOrigin::class.java) {
    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): PointDetailOrOrigin {
        val node = context.readTree(parser)
        return if (node.isObject) {
            context.readTreeAsValue(node, PointDetail::class.java)
        } else {
            context.readTreeAsValue(node, OriginString::class.java)
        }
    }
}
