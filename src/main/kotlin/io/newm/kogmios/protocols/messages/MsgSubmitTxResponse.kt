package io.newm.kogmios.protocols.messages

import io.newm.kogmios.ClientImpl.Companion.json
import io.newm.kogmios.protocols.model.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import java.math.BigInteger

/**
 * Response that comes back from Ogmios after a FindIntersect message is sent.
 */
@Serializable
@SerialName("SubmitTx")
data class MsgSubmitTxResponse(
    @SerialName("result")
    val result: SubmitTxResult,
    @SerialName("reflection")
    override val reflection: String,
) : JsonWspResponse()

@Serializable(with = SubmitTxResultSerializer::class)
sealed class SubmitTxResult

@Serializable(with = SubmitSuccessSerializer::class)
data class SubmitSuccess(
    @SerialName("txId")
    val txId: String
) : SubmitTxResult()

@Serializable(with = SubmitFailSerializer::class)
class SubmitFail : SubmitTxResult(), MutableList<SubmitFailItem> by mutableListOf() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SubmitFail) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "SubmitFail([${this.joinToString { submitFailItem -> submitFailItem.toString() }}])"
    }
}

object SubmitTxResultSerializer : JsonContentPolymorphicSerializer<SubmitTxResult>(SubmitTxResult::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out SubmitTxResult> {
        return if ("SubmitFail" in element.jsonObject) {
            SubmitFail.serializer()
        } else if ("SubmitSuccess" in element.jsonObject) {
            SubmitSuccess.serializer()
        } else {
            throw IllegalStateException("No Serializer found to decode: $element")
        }
    }
}

object SubmitFailSerializer : KSerializer<SubmitFail> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SubmitFail")

    override fun deserialize(decoder: Decoder): SubmitFail {
        require(decoder is JsonDecoder)
        val submitFail = SubmitFail()
        val listJsonElement = decoder.decodeJsonElement()
        listJsonElement.jsonObject["SubmitFail"]?.jsonArray?.forEach { listItemJsonElement ->
            val item: SubmitFailItem = json.decodeFromJsonElement(listItemJsonElement)
            submitFail.add(item)
        }
        return submitFail
    }

    override fun serialize(encoder: Encoder, value: SubmitFail) {
    }
}

object SubmitSuccessSerializer : KSerializer<SubmitSuccess> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SubmitSuccess")

    override fun deserialize(decoder: Decoder): SubmitSuccess {
        require(decoder is JsonDecoder)
        val jsonElement = decoder.decodeJsonElement()
        return if ("txId" in jsonElement.jsonObject) {
            SubmitSuccess(jsonElement.jsonObject["txId"]!!.jsonPrimitive.content)
        } else {
            json.decodeFromJsonElement(jsonElement.jsonObject["SubmitSuccess"]!!)
        }
    }

    override fun serialize(encoder: Encoder, value: SubmitSuccess) {
    }
}

@Serializable(with = SubmitFailItemSerializer::class)
sealed class SubmitFailItem

@Serializable
data class EraMismatchSubmitFailItem(
    @SerialName("eraMismatch")
    val eraMismatch: EraMismatch
) : SubmitFailItem()

@Serializable
data class EraMismatch(
    @SerialName("queryEra")
    val queryEra: String,
    @SerialName("ledgerEra")
    val ledgerEra: String,
)

@Serializable
data class InvalidWitnessesSubmitFailItem(
    @SerialName("invalidWitnesses")
    val invalidWitnesses: List<String>,
) : SubmitFailItem()

@Serializable
data class MissingVkWitnessesSubmitFailItem(
    @SerialName("missingVkWitnesses")
    val missingVkWitnesses: List<String>,
) : SubmitFailItem()

@Serializable
data class MissingScriptWitnessesSubmitFailItem(
    @SerialName("missingScriptWitnesses")
    val missingScriptWitnesses: List<String>,
) : SubmitFailItem()

@Serializable
data class ScriptWitnessNotValidatingSubmitFailItem(
    @SerialName("scriptWitnessNotValidating")
    val scriptWitnessNotValidating: List<String>,
) : SubmitFailItem()

@Serializable
data class InsufficientGenesisSignaturesSubmitFailItem(
    @SerialName("insufficientGenesisSignatures")
    val insufficientGenesisSignatures: List<String>,
) : SubmitFailItem()

@Serializable
data class MissingTxMetadataSubmitFailItem(
    @SerialName("missingTxMetadata")
    val missingTxMetadata: String,
) : SubmitFailItem()

@Serializable
data class MissingTxMetadataHashSubmitFailItem(
    @SerialName("missingTxMetadataHash")
    val missingTxMetadataHash: String,
) : SubmitFailItem()

@Serializable
data class TxMetadataHashMismatchSubmitFailItem(
    @SerialName("txMetadataHashMismatch")
    val txMetadataHashMismatch: TxMetadataHashMismatch,
) : SubmitFailItem()

@Serializable
data class TxMetadataHashMismatch(
    @SerialName("includedHash")
    val includedHash: String,
    @SerialName("expectedHash")
    val expectedHash: String,
)

@Serializable
data class BadInputsSubmitFailItem(
    @SerialName("badInputs")
    val badInputs: List<UtxoInput>,
) : SubmitFailItem()

@Serializable
data class ExpiredUtxoSubmitFailItem(
    @SerialName("expiredUtxo")
    val expiredUtxo: ExpiredUtxo,
) : SubmitFailItem()

@Serializable
data class ExpiredUtxo(
    @SerialName("currentSlot")
    @Contextual
    val currentSlot: BigInteger,
    @SerialName("transactionTimeToLive")
    @Contextual
    val transactionTimeToLive: BigInteger,
)

@Serializable
data class OutsideOfValidityIntervalSubmitFailItem(
    @SerialName("outsideOfValidityInterval")
    val outsideOfValidityInterval: OutsideOfValidityInterval
) : SubmitFailItem()

@Serializable
data class OutsideOfValidityInterval(
    @SerialName("currentSlot")
    @Contextual
    val currentSlot: BigInteger,
    @SerialName("interval")
    val interval: ValidityInterval,
)

@Serializable
data class TxTooLargeSubmitFailItem(
    @SerialName("txTooLarge")
    val txTooLarge: TxTooLarge,
) : SubmitFailItem()

@Serializable
data class TxTooLarge(
    @SerialName("maximumSize")
    val maximumSize: Long,
    @SerialName("actualSize")
    val actualSize: Long,
)

@Serializable
data class MissingAtLeastOneInputUtxoSubmitFailItem(
    @SerialName("missingAtLeastOneInputUtxo")
    val missingAtLeastOneInputUtxo: String?, // note: will always be null
) : SubmitFailItem()

@Serializable
data class InvalidMetadataSubmitFailItem(
    @SerialName("invalidMetadata")
    val invalidMetadata: String?, // note: will always be null
) : SubmitFailItem()

@Serializable
data class FeeTooSmallSubmitFailItem(
    @SerialName("feeTooSmall")
    val feeTooSmall: FeeTooSmall,
) : SubmitFailItem()

@Serializable
data class FeeTooSmall(
    @SerialName("requiredFee")
    val requiredFee: Long,
    @SerialName("actualFee")
    val actualFee: Long,
)

@Serializable
data class ValueNotConservedSubmitFailItem(
    @SerialName("valueNotConserved")
    val valueNotConserved: ValueNotConserved,
) : SubmitFailItem()

@Serializable(with = ValueNotConservedSerializer::class)
data class ValueNotConserved(
    @SerialName("consumed")
    val consumed: UtxoOutputValue,
    @SerialName("produced")
    val produced: UtxoOutputValue,
)

object ValueNotConservedSerializer : KSerializer<ValueNotConserved> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ValueNotConserved")

    override fun deserialize(decoder: Decoder): ValueNotConserved {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        val consumed = if (element.jsonObject["consumed"] is JsonPrimitive) {
            UtxoOutputValue(
                coins = element.jsonObject["consumed"]?.jsonPrimitive?.long?.toBigInteger() ?: BigInteger.ZERO
            )
        } else {
            json.decodeFromJsonElement(element.jsonObject["consumed"]!!)
        }
        val produced = if (element.jsonObject["produced"] is JsonPrimitive) {
            UtxoOutputValue(
                coins = element.jsonObject["produced"]?.jsonPrimitive?.long?.toBigInteger() ?: BigInteger.ZERO
            )
        } else {
            json.decodeFromJsonElement(element.jsonObject["produced"]!!)
        }
        return ValueNotConserved(consumed, produced)
    }

    override fun serialize(encoder: Encoder, value: ValueNotConserved) {
        // not implemented
    }
}

@Serializable
data class NetworkMismatchSubmitFailItem(
    @SerialName("networkMismatch")
    val networkMismatch: NetworkMismatch,
) : SubmitFailItem()

@Serializable
data class NetworkMismatch(
    @SerialName("expectedNetwork")
    val expectedNetwork: String,
    @SerialName("invalidEntities")
    val invalidEntities: List<InvalidEntity>
)

@Serializable
data class InvalidEntity(
    @SerialName("type")
    val type: String,
    @SerialName("entity")
    val entity: String,
)

@Serializable
data class OutputTooSmallSubmitFailItem(
    @SerialName("outputTooSmall")
    val outputTooSmall: List<UtxoOutput>
) : SubmitFailItem()

@Serializable
data class TooManyAssetsInOutputSubmitFailItem(
    @SerialName("tooManyAssetsInOutput")
    val tooManyAssetsInOutput: List<UtxoOutput>
) : SubmitFailItem()

@Serializable
data class AddressAttributesTooLargeSubmitFailItem(
    @SerialName("addressAttributesTooLarge")
    val addressAttributesTooLarge: List<String>
) : SubmitFailItem()

@Serializable
data class TriesToForgeAdaSubmitFailItem(
    @SerialName("triesToForgeAda")
    val triesToForgeAda: String?
) : SubmitFailItem()

@Serializable
data class DelegateNotRegisteredSubmitFailItem(
    @SerialName("delegateNotRegistered")
    val delegateNotRegistered: String
) : SubmitFailItem()

@Serializable
data class UnknownOrIncompleteWithdrawalsSubmitFailItem(
    @SerialName("unknownOrIncompleteWithdrawals")
    val unknownOrIncompleteWithdrawals: Map<String, @Contextual BigInteger>
) : SubmitFailItem()

@Serializable
data class StakePoolNotRegisteredSubmitFailItem(
    @SerialName("stakePoolNotRegistered")
    val stakePoolNotRegistered: String
) : SubmitFailItem()

@Serializable
data class WrongRetirementEpochSubmitFailItem(
    @SerialName("wrongRetirementEpoch")
    val wrongRetirementEpoch: WrongRetirementEpoch
) : SubmitFailItem()

@Serializable
data class WrongRetirementEpoch(
    @SerialName("currentEpoch")
    @Contextual
    val currentEpoch: BigInteger,
    @SerialName("requestedEpoch")
    @Contextual
    val requestedEpoch: BigInteger,
    @SerialName("firstUnreachableEpoch")
    @Contextual
    val firstUnreachableEpoch: BigInteger,
)

@Serializable
data class WrongPoolCertificateSubmitFailItem(
    @SerialName("wrongPoolCertificate")
    val wrongPoolCertificate: Int
) : SubmitFailItem()

@Serializable
data class StakeKeyAlreadyRegisteredSubmitFailItem(
    @SerialName("stakeKeyAlreadyRegistered")
    val stakeKeyAlreadyRegistered: String
) : SubmitFailItem()

@Serializable
data class PoolCostTooSmallSubmitFailItem(
    @SerialName("poolCostTooSmall")
    val poolCostTooSmall: PoolCostTooSmall
) : SubmitFailItem()

@Serializable
data class PoolCostTooSmall(
    @SerialName("minimumCost")
    @Contextual
    val minimumCost: BigInteger,
)

@Serializable
data class PoolMetadataHashTooBigSubmitFailItem(
    @SerialName("poolMetadataHashTooBig")
    val poolMetadataHashTooBig: PoolMetadataHashTooBig
) : SubmitFailItem()

@Serializable
data class PoolMetadataHashTooBig(
    @SerialName("poolId")
    val poolId: String,
    @SerialName("measuredSize")
    @Contextual
    val measuredSize: BigInteger,
)

@Serializable
data class StakeKeyNotRegisteredSubmitFailItem(
    @SerialName("stakeKeyNotRegistered")
    val stakeKeyNotRegistered: String
) : SubmitFailItem()

@Serializable
data class RewardAccountNotExistingSubmitFailItem(
    @SerialName("rewardAccountNotExisting")
    val rewardAccountNotExisting: String?
) : SubmitFailItem()

@Serializable
data class RewardAccountNotEmptySubmitFailItem(
    @SerialName("rewardAccountNotEmpty")
    val rewardAccountNotEmpty: RewardAccountNotEmpty
) : SubmitFailItem()

@Serializable
data class RewardAccountNotEmpty(
    @SerialName("balance")
    @Contextual
    val balance: BigInteger,
)

@Serializable
data class WrongCertificateTypeSubmitFailItem(
    @SerialName("wrongCertificateType")
    val wrongCertificateType: String?
) : SubmitFailItem()

@Serializable
data class UnknownGenesisKeySubmitFailItem(
    @SerialName("unknownGenesisKey")
    val unknownGenesisKey: String
) : SubmitFailItem()

@Serializable
data class AlreadyDelegatingSubmitFailItem(
    @SerialName("alreadyDelegating")
    val alreadyDelegating: String
) : SubmitFailItem()

@Serializable
data class InsufficientFundsForMirSubmitFailItem(
    @SerialName("insufficientFundsForMir")
    val insufficientFundsForMir: InsufficientFundsForMir
) : SubmitFailItem()

@Serializable
data class InsufficientFundsForMir(
    @SerialName("rewardSource")
    val rewardSource: String,
    @SerialName("sourceSize")
    @Contextual
    val sourceSize: BigInteger,
    @SerialName("requestedAmount")
    @Contextual
    val requestedAmount: BigInteger,
)

@Serializable
data class TooLateForMirSubmitFailItem(
    @SerialName("tooLateForMir")
    val tooLateForMir: TooLateForMir
) : SubmitFailItem()

@Serializable
data class TooLateForMir(
    @SerialName("currentSlot")
    @Contextual
    val currentSlot: BigInteger,
    @SerialName("lastAllowedSlot")
    @Contextual
    val lastAllowedSlot: BigInteger,
)

@Serializable
data class MirTransferNotCurrentlyAllowedSubmitFailItem(
    @SerialName("mirTransferNotCurrentlyAllowed")
    val mirTransferNotCurrentlyAllowed: String?
) : SubmitFailItem()

@Serializable
data class MirNegativeTransferNotCurrentlyAllowedSubmitFailItem(
    @SerialName("mirNegativeTransferNotCurrentlyAllowed")
    val mirNegativeTransferNotCurrentlyAllowed: String?
) : SubmitFailItem()

@Serializable
data class MirProducesNegativeUpdateSubmitFailItem(
    @SerialName("mirProducesNegativeUpdate")
    val mirProducesNegativeUpdate: String?
) : SubmitFailItem()

@Serializable
data class DuplicateGenesisVrfSubmitFailItem(
    @SerialName("duplicateGenesisVrf")
    val duplicateGenesisVrf: String
) : SubmitFailItem()

@Serializable
data class NonGenesisVotersSubmitFailItem(
    @SerialName("nonGenesisVoters")
    val nonGenesisVoters: NonGenesisVoters
) : SubmitFailItem()

@Serializable
data class NonGenesisVoters(
    @SerialName("currentlyVoting")
    val currentlyVoting: List<String>,
    @SerialName("shouldBeVoting")
    val shouldBeVoting: List<String>,
)

@Serializable
data class UpdateWrongEpochSubmitFailItem(
    @SerialName("updateWrongEpoch")
    val updateWrongEpoch: UpdateWrongEpoch
) : SubmitFailItem()

@Serializable
data class UpdateWrongEpoch(
    @SerialName("currentEpoch")
    @Contextual
    val currentEpoch: BigInteger,
    @SerialName("requestedEpoch")
    @Contextual
    val requestedEpoch: BigInteger,
    @SerialName("votingPeriod")
    val votingPeriod: String,
)

@Serializable
data class ProtocolVersionCannotFollowSubmitFailItem(
    @SerialName("protocolVersionCannotFollow")
    val protocolVersionCannotFollow: ProtocolVersion
) : SubmitFailItem()

@Serializable
data class MissingRequiredRedeemersSubmitFailItem(
    @SerialName("missingRequiredRedeemers")
    val missingRequiredRedeemers: MissingRequiredRedeemers
) : SubmitFailItem()

@Serializable
data class MissingRequiredRedeemers(
    @SerialName("missing")
    val missing: List<Map<String, Redeemer>>
)

@Serializable(with = RedeemerSerializer::class)
sealed class Redeemer

@Serializable
data class SpendRedeemer(
    @SerialName("spend")
    val spend: UtxoInput
) : Redeemer()

@Serializable
data class MintRedeemer(
    @SerialName("mint")
    val mint: String
) : Redeemer()

@Serializable
data class CertificateRedeemer(
    @SerialName("certificate")
    val certificate: Certificate
) : Redeemer()

@Serializable
data class WithdrawalRedeemer(
    @SerialName("withdrawal")
    val withdrawal: String
) : Redeemer()

object RedeemerSerializer : JsonContentPolymorphicSerializer<Redeemer>(Redeemer::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Redeemer> {
        return if ("spend" in element.jsonObject) {
            SpendRedeemer.serializer()
        } else if ("mint" in element.jsonObject) {
            MintRedeemer.serializer()
        } else {
            throw IllegalStateException("No Serializer found to decode: $element")
        }
    }
}

@Serializable
data class MissingRequiredDatumsSubmitFailItem(
    @SerialName("missingRequiredDatums")
    val missingRequiredDatums: MissingRequiredDatums
) : SubmitFailItem()

@Serializable
data class MissingRequiredDatums(
    @SerialName("provided")
    val provided: List<String>,
    @SerialName("missing")
    val missing: List<String>,
)

@Serializable
data class UnspendableDatumsSubmitFailItem(
    @SerialName("unspendableDatums")
    val unspendableDatums: UnspendableDatums
) : SubmitFailItem()

@Serializable
data class UnspendableDatums(
    @SerialName("nonSpendable")
    val nonSpendable: List<String>,
    @SerialName("acceptable")
    val acceptable: List<String>,
)

@Serializable
data class ExtraDataMismatchSubmitFailItem(
    @SerialName("extraDataMismatch")
    val extraDataMismatch: ExtraDataMismatch
) : SubmitFailItem()

@Serializable
data class ExtraDataMismatch(
    @SerialName("provided")
    val provided: String?,
    @SerialName("inferredFromParameters")
    val inferredFromParameters: String?,
)

@Serializable
data class MissingRequiredSignaturesSubmitFailItem(
    @SerialName("MissingRequiredSignatures")
    val missingRequiredSignatures: List<String>
) : SubmitFailItem()

@Serializable
data class UnspendableScriptInputsSubmitFailItem(
    @SerialName("unspendableScriptInputs")
    val unspendableScriptInputs: List<UtxoInput>
) : SubmitFailItem()

@Serializable
data class ExtraRedeemersSubmitFailItem(
    @SerialName("extraRedeemers")
    val extraRedeemers: List<String>
) : SubmitFailItem()

@Serializable
data class MissingDatumHashesForInputsSubmitFailItem(
    @SerialName("missingDatumHashesForInputs")
    val missingDatumHashesForInputs: List<UtxoInput>
) : SubmitFailItem()

@Serializable
data class MissingCollateralInputsSubmitFailItem(
    @SerialName("missingCollateralInputs")
    val missingCollateralInputs: String?
) : SubmitFailItem()

@Serializable
data class CollateralTooSmallSubmitFailItem(
    @SerialName("collateralTooSmall")
    val collateralTooSmall: CollateralTooSmall
) : SubmitFailItem()

@Serializable
data class CollateralTooSmall(
    @SerialName("requiredCollateral")
    @Contextual
    val requiredCollateral: BigInteger,
    @SerialName("actualCollateral")
    @Contextual
    val actualCollateral: BigInteger,
)

@Serializable
data class CollateralIsScriptSubmitFailItem(
    @SerialName("collateralIsScript")
    val collateralIsScript: List<Pair<UtxoInput, UtxoOutput>>
) : SubmitFailItem()

@Serializable
data class CollateralHasNonAdaAssetsSubmitFailItem(
    @SerialName("collateralHasNonAdaAssets")
    val collateralHasNonAdaAssets: UtxoOutputValue
) : SubmitFailItem()

@Serializable
data class TooManyCollateralInputsSubmitFailItem(
    @SerialName("tooManyCollateralInputs")
    val tooManyCollateralInputs: TooManyCollateralInputs
) : SubmitFailItem()

@Serializable
data class TooManyCollateralInputs(
    @SerialName("maximumCollateralInputs")
    @Contextual
    val maximumCollateralInputs: BigInteger,
    @SerialName("actualCollateralInputs")
    @Contextual
    val actualCollateralInputs: BigInteger,
)

@Serializable
data class ExecutionUnitsTooLargeSubmitFailItem(
    @SerialName("executionUnitsTooLarge")
    val executionUnitsTooLarge: ExecutionUnitsTooLarge
) : SubmitFailItem()

@Serializable
data class ExecutionUnitsTooLarge(
    @SerialName("maximumExecutionUnits")
    val maximumExecutionUnits: ExecutionUnits,
    @SerialName("actualExecutionUnits")
    val actualExecutionUnits: ExecutionUnits,
)

@Serializable
data class OutsideForecastSubmitFailItem(
    @SerialName("outsideForecast")
    @Contextual
    val outsideForecast: BigInteger
) : SubmitFailItem()

@Serializable
data class ValidationTagMismatchSubmitFailItem(
    @SerialName("validationTagMismatch")
    val validationTagMismatch: String?
) : SubmitFailItem()

@Serializable
data class CollectErrorsSubmitFailItem(
    @SerialName("collectErrors")
    val collectErrors: List<CollectError>
) : SubmitFailItem()

@Serializable(with = CollectErrorSerializer::class)
sealed class CollectError

@Serializable
data class NoRedeemerCollectError(
    @SerialName("noRedeemer")
    val noRedeemer: Redeemer
) : CollectError()

@Serializable
data class NoWitnessCollectError(
    @SerialName("noWitness")
    val noWitness: String
) : CollectError()

@Serializable
data class NoCostModelCollectError(
    @SerialName("noCostModel")
    val noCostModel: String
) : CollectError()

@Serializable
data class BadTranslationCollectError(
    @SerialName("badTranslation")
    val badTranslation: String
) : CollectError()

object CollectErrorSerializer : JsonContentPolymorphicSerializer<CollectError>(CollectError::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out CollectError> {
        return if ("noRedeemer" in element.jsonObject) {
            NoRedeemerCollectError.serializer()
        } else if ("noWitness" in element.jsonObject) {
            NoWitnessCollectError.serializer()
        } else if ("noCostModel" in element.jsonObject) {
            NoCostModelCollectError.serializer()
        } else if ("badTranslation" in element.jsonObject) {
            BadTranslationCollectError.serializer()
        } else {
            throw IllegalStateException("No Serializer found to decode: $element")
        }
    }
}

@Serializable
data class ExtraScriptWitnessesSubmitFailItem(
    @SerialName("extraScriptWitnesses")
    val extraScriptWitnesses: List<String>
) : SubmitFailItem()

@Serializable
data class MirNegativeTransferSubmitFailItem(
    @SerialName("MirNegativeTransfer")
    val mirNegativeTransfer: MirNegativeTransfer
) : SubmitFailItem()

@Serializable
data class MirNegativeTransfer(
    @SerialName("rewardSource")
    val rewardSource: String,
    @SerialName("attemptedTransfer")
    @Contextual
    val attemptedTransfer: BigInteger,
)

@Serializable
data class TotalCollateralMismatchSubmitFailItem(
    @SerialName("totalCollateralMismatch")
    val totalCollateralMismatch: TotalCollateralMismatch
) : SubmitFailItem()

@Serializable
data class TotalCollateralMismatch(
    @SerialName("computedFromDelta")
    @Contextual
    val computedFromDelta: BigInteger,
    @SerialName("declaredInField")
    @Contextual
    val declaredInField: BigInteger,
)

@Serializable
data class MalformedReferenceScriptsSubmitFailItem(
    @SerialName("malformedReferenceScripts")
    val malformedReferenceScripts: List<String>
) : SubmitFailItem()

@Serializable
data class MalformedScriptWitnessesSubmitFailItem(
    @SerialName("malformedScriptWitnesses")
    val malformedScriptWitnesses: List<String>
) : SubmitFailItem()

object SubmitFailItemSerializer : JsonContentPolymorphicSerializer<SubmitFailItem>(SubmitFailItem::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out SubmitFailItem> {
        return if ("addressAttributesTooLarge" in element.jsonObject) {
            AddressAttributesTooLargeSubmitFailItem.serializer()
        } else if ("alreadyDelegating" in element.jsonObject) {
            AlreadyDelegatingSubmitFailItem.serializer()
        } else if ("badInputs" in element.jsonObject) {
            BadInputsSubmitFailItem.serializer()
        } else if ("collateralHasNonAdaAssets" in element.jsonObject) {
            CollateralHasNonAdaAssetsSubmitFailItem.serializer()
        } else if ("collateralIsScript" in element.jsonObject) {
            CollateralIsScriptSubmitFailItem.serializer()
        } else if ("collateralTooSmall" in element.jsonObject) {
            CollateralTooSmallSubmitFailItem.serializer()
        } else if ("collectErrors" in element.jsonObject) {
            CollectErrorsSubmitFailItem.serializer()
        } else if ("delegateNotRegistered" in element.jsonObject) {
            DelegateNotRegisteredSubmitFailItem.serializer()
        } else if ("duplicateGenesisVrf" in element.jsonObject) {
            DuplicateGenesisVrfSubmitFailItem.serializer()
        } else if ("eraMismatch" in element.jsonObject) {
            EraMismatchSubmitFailItem.serializer()
        } else if ("executionUnitsTooLarge" in element.jsonObject) {
            ExecutionUnitsTooLargeSubmitFailItem.serializer()
        } else if ("expiredUtxo" in element.jsonObject) {
            ExpiredUtxoSubmitFailItem.serializer()
        } else if ("extraDataMismatch" in element.jsonObject) {
            ExtraDataMismatchSubmitFailItem.serializer()
        } else if ("extraRedeemers" in element.jsonObject) {
            ExtraRedeemersSubmitFailItem.serializer()
        } else if ("extraScriptWitnesses" in element.jsonObject) {
            ExtraScriptWitnessesSubmitFailItem.serializer()
        } else if ("feeTooSmall" in element.jsonObject) {
            FeeTooSmallSubmitFailItem.serializer()
        } else if ("insufficientFundsForMir" in element.jsonObject) {
            InsufficientFundsForMirSubmitFailItem.serializer()
        } else if ("insufficientGenesisSignatures" in element.jsonObject) {
            InsufficientGenesisSignaturesSubmitFailItem.serializer()
        } else if ("invalidMetadata" in element.jsonObject) {
            InvalidMetadataSubmitFailItem.serializer()
        } else if ("invalidWitnesses" in element.jsonObject) {
            InvalidWitnessesSubmitFailItem.serializer()
        } else if ("malformedReferenceScripts" in element.jsonObject) {
            MalformedReferenceScriptsSubmitFailItem.serializer()
        } else if ("malformedScriptWitnesses" in element.jsonObject) {
            MalformedScriptWitnessesSubmitFailItem.serializer()
        } else if ("mirNegativeTransferNotCurrentlyAllowed" in element.jsonObject) {
            MirNegativeTransferNotCurrentlyAllowedSubmitFailItem.serializer()
        } else if ("mirNegativeTransfer" in element.jsonObject) {
            MirNegativeTransferSubmitFailItem.serializer()
        } else if ("mirProducesNegativeUpdate" in element.jsonObject) {
            MirProducesNegativeUpdateSubmitFailItem.serializer()
        } else if ("mirTransferNotCurrentlyAllowed" in element.jsonObject) {
            MirTransferNotCurrentlyAllowedSubmitFailItem.serializer()
        } else if ("missingAtLeastOneInputUtxo" in element.jsonObject) {
            MissingAtLeastOneInputUtxoSubmitFailItem.serializer()
        } else if ("missingCollateralInputs" in element.jsonObject) {
            MissingCollateralInputsSubmitFailItem.serializer()
        } else if ("missingDatumHashesForInputs" in element.jsonObject) {
            MissingDatumHashesForInputsSubmitFailItem.serializer()
        } else if ("missingRequiredDatums" in element.jsonObject) {
            MissingRequiredDatumsSubmitFailItem.serializer()
        } else if ("missingRequiredRedeemers" in element.jsonObject) {
            MissingRequiredRedeemersSubmitFailItem.serializer()
        } else if ("missingRequiredSignatures" in element.jsonObject) {
            MissingRequiredSignaturesSubmitFailItem.serializer()
        } else if ("missingScriptWitnesses" in element.jsonObject) {
            MissingScriptWitnessesSubmitFailItem.serializer()
        } else if ("missingTxMetadataHash" in element.jsonObject) {
            MissingTxMetadataHashSubmitFailItem.serializer()
        } else if ("missingTxMetadata" in element.jsonObject) {
            MissingTxMetadataSubmitFailItem.serializer()
        } else if ("missingVkWitnesses" in element.jsonObject) {
            MissingVkWitnessesSubmitFailItem.serializer()
        } else if ("networkMismatch" in element.jsonObject) {
            NetworkMismatchSubmitFailItem.serializer()
        } else if ("nonGenesisVoters" in element.jsonObject) {
            NonGenesisVotersSubmitFailItem.serializer()
        } else if ("outputTooSmall" in element.jsonObject) {
            OutputTooSmallSubmitFailItem.serializer()
        } else if ("outsideForecast" in element.jsonObject) {
            OutsideForecastSubmitFailItem.serializer()
        } else if ("outsideOfValidityInterval" in element.jsonObject) {
            OutsideOfValidityIntervalSubmitFailItem.serializer()
        } else if ("poolCostTooSmall" in element.jsonObject) {
            PoolCostTooSmallSubmitFailItem.serializer()
        } else if ("poolMetadataHashTooBig" in element.jsonObject) {
            PoolMetadataHashTooBigSubmitFailItem.serializer()
        } else if ("protocolVersionCannotFollow" in element.jsonObject) {
            ProtocolVersionCannotFollowSubmitFailItem.serializer()
        } else if ("rewardAccountNotEmpty" in element.jsonObject) {
            RewardAccountNotEmptySubmitFailItem.serializer()
        } else if ("rewardAccountNotExisting" in element.jsonObject) {
            RewardAccountNotExistingSubmitFailItem.serializer()
        } else if ("scriptWitnessNotValidating" in element.jsonObject) {
            ScriptWitnessNotValidatingSubmitFailItem.serializer()
        } else if ("stakeKeyAlreadyRegistered" in element.jsonObject) {
            StakeKeyAlreadyRegisteredSubmitFailItem.serializer()
        } else if ("stakeKeyNotRegistered" in element.jsonObject) {
            StakeKeyNotRegisteredSubmitFailItem.serializer()
        } else if ("stakePoolNotRegistered" in element.jsonObject) {
            StakePoolNotRegisteredSubmitFailItem.serializer()
        } else if ("tooLateForMir" in element.jsonObject) {
            TooLateForMirSubmitFailItem.serializer()
        } else if ("tooManyAssetsInOutput" in element.jsonObject) {
            TooManyAssetsInOutputSubmitFailItem.serializer()
        } else if ("tooManyCollateralInputs" in element.jsonObject) {
            TooManyCollateralInputsSubmitFailItem.serializer()
        } else if ("totalCollateralMismatch" in element.jsonObject) {
            TotalCollateralMismatchSubmitFailItem.serializer()
        } else if ("triesToForgeAda" in element.jsonObject) {
            TriesToForgeAdaSubmitFailItem.serializer()
        } else if ("txMetadataHashMismatch" in element.jsonObject) {
            TxMetadataHashMismatchSubmitFailItem.serializer()
        } else if ("txTooLarge" in element.jsonObject) {
            TxTooLargeSubmitFailItem.serializer()
        } else if ("unknownGenesisKey" in element.jsonObject) {
            UnknownGenesisKeySubmitFailItem.serializer()
        } else if ("unknownOrIncompleteWithdrawals" in element.jsonObject) {
            UnknownOrIncompleteWithdrawalsSubmitFailItem.serializer()
        } else if ("unspendableDatums" in element.jsonObject) {
            UnspendableDatumsSubmitFailItem.serializer()
        } else if ("unspendableScriptInputs" in element.jsonObject) {
            UnspendableScriptInputsSubmitFailItem.serializer()
        } else if ("updateWrongEpoch" in element.jsonObject) {
            UpdateWrongEpochSubmitFailItem.serializer()
        } else if ("validationTagMismatch" in element.jsonObject) {
            ValidationTagMismatchSubmitFailItem.serializer()
        } else if ("valueNotConserved" in element.jsonObject) {
            ValueNotConservedSubmitFailItem.serializer()
        } else if ("wrongCertificateType" in element.jsonObject) {
            WrongCertificateTypeSubmitFailItem.serializer()
        } else if ("wrongPoolCertificate" in element.jsonObject) {
            WrongPoolCertificateSubmitFailItem.serializer()
        } else if ("wrongRetirementEpoch" in element.jsonObject) {
            WrongRetirementEpochSubmitFailItem.serializer()
        } else {
            throw IllegalStateException("No Serializer found to decode: $element")
        }
    }
}
