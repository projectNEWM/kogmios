package io.newm.kogmios.protocols.model.serializers

import io.newm.kogmios.protocols.model.UpdateProposal
import io.newm.kogmios.protocols.model.UpdateProposalAlonzo
import io.newm.kogmios.protocols.model.UpdateProposalBabbage
import io.newm.kogmios.protocols.model.UpdateProposalShelley
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

object UpdateProposalSerializer : JsonContentPolymorphicSerializer<UpdateProposal>(UpdateProposal::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<UpdateProposal> {
        return if ("coinsPerUtxoByte" in element.jsonObject) {
            UpdateProposalBabbage.serializer()
        } else if ("coinsPerUtxoWord" in element.jsonObject) {
            UpdateProposalAlonzo.serializer()
        } else {
            UpdateProposalShelley.serializer()
        }
    }
}
