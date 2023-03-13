package io.newm.kogmios.protocols.messages

import io.newm.kogmios.ClientImpl.Companion.json
import io.newm.kogmios.protocols.model.ExecutionUnits
import io.newm.kogmios.protocols.model.UtxoInput
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

/**
 * Response that comes back from Ogmios after a MsgEvaluateTx message is sent.
 */
@Serializable
@SerialName("EvaluateTx")
data class MsgEvaluateTxResponse(
    @SerialName("result")
    val result: EvaluateTxResult,
    @SerialName("reflection")
    override val reflection: String,
) : JsonWspResponse()

@Serializable(with = EvaluateTxResultSerializer::class)
sealed class EvaluateTxResult

@Serializable(with = EvaluationResultSerializer::class)
class EvaluationResult : EvaluateTxResult(), MutableMap<String, ExecutionUnits> by mutableMapOf() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EvaluationResult) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "EvaluationResult([${this.entries.joinToString { entry -> "\"${entry.key}\": ${entry.value}" }}])"
    }
}

object EvaluateTxResultSerializer : JsonContentPolymorphicSerializer<EvaluateTxResult>(EvaluateTxResult::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<EvaluateTxResult> {
        return with(element.jsonObject) {
            when {
                contains("EvaluationResult") -> EvaluationResult.serializer()
                contains("EvaluationFailure") -> EvaluationFailureTxResult.serializer()
                else -> throw IllegalStateException("No Serializer found to decode: $element")
            }
        }
    }
}

object EvaluationResultSerializer : KSerializer<EvaluationResult> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("EvaluationResult")

    override fun deserialize(decoder: Decoder): EvaluationResult {
        require(decoder is JsonDecoder)
        val evaluationResult = EvaluationResult()
        val listJsonElement = decoder.decodeJsonElement()
        listJsonElement.jsonObject["EvaluationResult"]?.jsonObject?.forEach { evaluationResultItem ->
            val key = evaluationResultItem.key
            val value: ExecutionUnits = json.decodeFromJsonElement(evaluationResultItem.value)
            evaluationResult[key] = value
        }
        return evaluationResult
    }

    override fun serialize(encoder: Encoder, value: EvaluationResult) {
    }
}

@Serializable
data class EvaluationFailureTxResult(
    @SerialName("EvaluationFailure")
    val evaluationFailure: EvaluationFailure,
) : EvaluateTxResult()

@Serializable(with = EvaluationFailureSerializer::class)
sealed class EvaluationFailure

@Serializable
data class ScriptEvaluationFailure(
    @SerialName("ScriptFailures")
    val scriptFailures: Map<String, List<ScriptFailureItem>>
) : EvaluationFailure()

@Serializable
data class IncompatibleEraEvaluationFailure(
    @SerialName("IncompatibleEra")
    val incompatibleEra: String,
) : EvaluationFailure()

@Serializable
data class AdditionalUtxoOverlapEvaluationFailure(
    @SerialName("AdditionalUtxoOverlap")
    val additionalUtxoOverlap: List<UtxoInput>,
) : EvaluationFailure()

@Serializable
data class NotEnoughSyncedEvaluationFailure(
    @SerialName("NotEnoughSynced")
    val notEnoughSynced: NotEnoughSynced,
) : EvaluationFailure()

@Serializable
data class CannotCreateEvaluationContextEvaluationFailure(
    @SerialName("CannotCreateEvaluationContext")
    val cannotCreateEvaluationContext: CannotCreateEvaluationContext,
) : EvaluationFailure()

object EvaluationFailureSerializer : JsonContentPolymorphicSerializer<EvaluationFailure>(EvaluationFailure::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<EvaluationFailure> {
        return with(element.jsonObject) {
            when {
                contains("ScriptFailures") -> ScriptEvaluationFailure.serializer()
                contains("IncompatibleEra") -> IncompatibleEraEvaluationFailure.serializer()
                contains("AdditionalUtxoOverlap") -> AdditionalUtxoOverlapEvaluationFailure.serializer()
                contains("NotEnoughSynced") -> NotEnoughSyncedEvaluationFailure.serializer()
                contains("CannotCreateEvaluationContext") -> CannotCreateEvaluationContextEvaluationFailure.serializer()
                else -> throw IllegalStateException("No Serializer found to decode: $element")
            }
        }
    }
}

@Serializable
data class NotEnoughSynced(
    @SerialName("minimumRequiredEra")
    val minimumRequiredEra: String,
    @SerialName("currentNodeEra")
    val currentNodeEra: String,
)

@Serializable
data class CannotCreateEvaluationContext(
    @SerialName("reason")
    val reason: String,
)

@Serializable(with = ScriptFailureItemSerializer::class)
sealed class ScriptFailureItem

@Serializable
data class ExtraRedeemersScriptFailureItem(
    @SerialName("extraRedeemers")
    val extraRedeemers: List<String>,
) : ScriptFailureItem()

@Serializable
data class MissingRequiredDatumsFailureItem(
    @SerialName("missingRequiredDatums")
    val missingRequiredDatums: MissingRequiredDatums,
) : ScriptFailureItem()

@Serializable
data class MissingRequiredScriptsScriptFailureItem(
    @SerialName("missingRequiredScripts")
    val missingRequiredScripts: MissingRequiredScripts,
) : ScriptFailureItem()

@Serializable
data class MissingRequiredScripts(
    @SerialName("missing")
    val missing: List<String>,
    @SerialName("resolved")
    val resolved: Map<String, String>,
)

@Serializable
data class ValidatorFailedFailureItem(
    @SerialName("validatorFailed")
    val validatorFailed: ValidatorFailed,
) : ScriptFailureItem()

@Serializable
data class ValidatorFailed(
    @SerialName("error")
    val error: String,
    @SerialName("traces")
    val traces: List<String>,
)

@Serializable
data class UnknownInputReferencedByRedeemerFailureItem(
    @SerialName("unknownInputReferencedByRedeemer")
    val unknownInputRefererencedByRedeemer: UtxoInput,
) : ScriptFailureItem()

@Serializable
data class NonScriptInputReferencedByRedeemerFailureItem(
    @SerialName("nonScriptInputReferencedByRedeemer")
    val nonScriptInputReferencedByRedeemer: UtxoInput,
) : ScriptFailureItem()

@Serializable
data class IllFormedExecutionBudgetFailureItem(
    @SerialName("illFormedExecutionBudget")
    val illFormedExecutionBudget: ExecutionUnits?,
) : ScriptFailureItem()

@Serializable
data class NoCostModelForLanguageFailureItem(
    @SerialName("noCostModelForLanguage")
    val noCostModelForLanguage: String,
) : ScriptFailureItem()

object ScriptFailureItemSerializer : JsonContentPolymorphicSerializer<ScriptFailureItem>(ScriptFailureItem::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<ScriptFailureItem> {
        return with(element.jsonObject) {
            when {
                contains("extraRedeemers") -> ExtraRedeemersScriptFailureItem.serializer()
                contains("missingRequiredDatums") -> MissingRequiredDatumsFailureItem.serializer()
                contains("missingRequiredScripts") -> MissingRequiredScriptsScriptFailureItem.serializer()
                contains("validatorFailed") -> ValidatorFailedFailureItem.serializer()
                contains("unknownInputReferencedByRedeemer") -> UnknownInputReferencedByRedeemerFailureItem.serializer()
                contains("nonScriptInputReferencedByRedeemer") -> NonScriptInputReferencedByRedeemerFailureItem.serializer()
                contains("illFormedExecutionBudget") -> IllFormedExecutionBudgetFailureItem.serializer()
                contains("noCostModelForLanguage") -> NoCostModelForLanguageFailureItem.serializer()
                else -> throw IllegalStateException("No Serializer found to decode: $element")
            }
        }
    }
}
