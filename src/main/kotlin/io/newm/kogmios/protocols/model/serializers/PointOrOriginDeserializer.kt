package io.newm.kogmios.protocols.model.serializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import io.newm.kogmios.protocols.model.Origin
import io.newm.kogmios.protocols.model.Point
import io.newm.kogmios.protocols.model.PointOrOrigin

internal class PointOrOriginDeserializer : StdDeserializer<PointOrOrigin>(PointOrOrigin::class.java) {
    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): PointOrOrigin {
        val node = context.readTree(parser)
        return if (node.path("point").isObject) {
            context.readTreeAsValue(node, Point::class.java)
        } else {
            context.readTreeAsValue(node, Origin::class.java)
        }
    }
}
