package io.newm.kogmios.protocols.model

import io.newm.kogmios.protocols.model.serializers.Blake2bDigestCredentialSerializer
import kotlinx.serialization.Serializable

@Serializable(with = Blake2bDigestCredentialSerializer::class)
class Blake2bDigestCredential(
    val digest: String,
) : NonMyopicMemberRewardsInput()
