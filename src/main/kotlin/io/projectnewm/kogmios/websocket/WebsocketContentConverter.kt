package io.projectnewm.kogmios.websocket

interface WebsocketContentConverter {
    fun serialize(value: Any): String
    fun deserialize(value: String): Any
}