package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.BlockSerializer
import kotlinx.serialization.Serializable

@Serializable(with = BlockSerializer::class)
sealed class Block {
    val header: BlockHeader
        get() = when (this) {
            is BlockAllegra -> this.allegra.header
            is BlockAlonzo -> this.alonzo.header
            is BlockBabbage -> this.babbage.header
            is BlockMary -> this.mary.header
            is BlockShelley -> this.shelley.header
        }
}
