package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.LovelaceInputSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable(with = LovelaceInputSerializer::class)
class LovelaceInput(
    @Contextual
    val amount: BigInteger
) : NonMyopicMemberRewardsInput()
