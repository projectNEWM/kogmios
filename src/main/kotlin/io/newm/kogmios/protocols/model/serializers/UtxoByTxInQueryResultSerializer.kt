package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.ClientImpl.Companion.json
import io.newm.kogmios.protocols.model.TxIn
import io.newm.kogmios.protocols.model.TxOut
import io.newm.kogmios.protocols.model.UtxoByTxInQueryResult
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonArray

object UtxoByTxInQueryResultSerializer : KSerializer<UtxoByTxInQueryResult> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("UtxoByTxInQueryResult")

    override fun deserialize(decoder: Decoder): UtxoByTxInQueryResult {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        val values = element.jsonArray.associate { utxoElement ->
            val txIn = json.decodeFromJsonElement(TxIn.serializer(), utxoElement.jsonArray[0])
            val txOut = json.decodeFromJsonElement(TxOut.serializer(), utxoElement.jsonArray[1])
            Pair(txIn, txOut)
        }
        return UtxoByTxInQueryResult(values)
    }

    override fun serialize(encoder: Encoder, value: UtxoByTxInQueryResult) {
        // Not Implemented
    }
}
