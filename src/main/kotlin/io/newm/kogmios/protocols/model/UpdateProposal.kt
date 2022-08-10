package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.UpdateProposalSerializer
import kotlinx.serialization.Serializable

@Serializable(with = UpdateProposalSerializer::class)
sealed class UpdateProposal
