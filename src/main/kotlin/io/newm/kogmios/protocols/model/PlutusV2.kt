package io.projectnewm.kogmios.protocols.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigInteger

@Serializable
data class PlutusV2(
    @Contextual
    @SerialName("addInteger-cpu-arguments-intercept")
    val addIntegerCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("addInteger-cpu-arguments-slope")
    val addIntegerCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("addInteger-memory-arguments-intercept")
    val addIntegerMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("addInteger-memory-arguments-slope")
    val addIntegerMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("appendByteString-cpu-arguments-intercept")
    val appendByteStringCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("appendByteString-cpu-arguments-slope")
    val appendByteStringCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("appendByteString-memory-arguments-intercept")
    val appendByteStringMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("appendByteString-memory-arguments-slope")
    val appendByteStringMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("appendString-cpu-arguments-intercept")
    val appendStringCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("appendString-cpu-arguments-slope")
    val appendStringCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("appendString-memory-arguments-intercept")
    val appendStringMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("appendString-memory-arguments-slope")
    val appendStringMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("bData-cpu-arguments")
    val bDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("bData-memory-arguments")
    val bDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("blake2b_256-cpu-arguments-intercept")
    val blake2b256CpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("blake2b_256-cpu-arguments-slope")
    val blake2b256CpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("blake2b_256-memory-arguments")
    val blake2b256MemoryArguments: BigInteger,
    @Contextual
    @SerialName("cekApplyCost-exBudgetCPU")
    val cekApplyCostExBudgetCPU: BigInteger,
    @Contextual
    @SerialName("cekApplyCost-exBudgetMemory")
    val cekApplyCostExBudgetMemory: BigInteger,
    @Contextual
    @SerialName("cekBuiltinCost-exBudgetCPU")
    val cekBuiltinCostExBudgetCPU: BigInteger,
    @Contextual
    @SerialName("cekBuiltinCost-exBudgetMemory")
    val cekBuiltinCostExBudgetMemory: BigInteger,
    @Contextual
    @SerialName("cekConstCost-exBudgetCPU")
    val cekConstCostExBudgetCPU: BigInteger,
    @Contextual
    @SerialName("cekConstCost-exBudgetMemory")
    val cekConstCostExBudgetMemory: BigInteger,
    @Contextual
    @SerialName("cekDelayCost-exBudgetCPU")
    val cekDelayCostExBudgetCPU: BigInteger,
    @Contextual
    @SerialName("cekDelayCost-exBudgetMemory")
    val cekDelayCostExBudgetMemory: BigInteger,
    @Contextual
    @SerialName("cekForceCost-exBudgetCPU")
    val cekForceCostExBudgetCPU: BigInteger,
    @Contextual
    @SerialName("cekForceCost-exBudgetMemory")
    val cekForceCostExBudgetMemory: BigInteger,
    @Contextual
    @SerialName("cekLamCost-exBudgetCPU")
    val cekLamCostExBudgetCPU: BigInteger,
    @Contextual
    @SerialName("cekLamCost-exBudgetMemory")
    val cekLamCostExBudgetMemory: BigInteger,
    @Contextual
    @SerialName("cekStartupCost-exBudgetCPU")
    val cekStartupCostExBudgetCPU: BigInteger,
    @Contextual
    @SerialName("cekStartupCost-exBudgetMemory")
    val cekStartupCostExBudgetMemory: BigInteger,
    @Contextual
    @SerialName("cekVarCost-exBudgetCPU")
    val cekVarCostExBudgetCPU: BigInteger,
    @Contextual
    @SerialName("cekVarCost-exBudgetMemory")
    val cekVarCostExBudgetMemory: BigInteger,
    @Contextual
    @SerialName("chooseData-cpu-arguments")
    val chooseDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("chooseData-memory-arguments")
    val chooseDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("chooseList-cpu-arguments")
    val chooseListCpuArguments: BigInteger,
    @Contextual
    @SerialName("chooseList-memory-arguments")
    val chooseListMemoryArguments: BigInteger,
    @Contextual
    @SerialName("chooseUnit-cpu-arguments")
    val chooseUnitCpuArguments: BigInteger,
    @Contextual
    @SerialName("chooseUnit-memory-arguments")
    val chooseUnitMemoryArguments: BigInteger,
    @Contextual
    @SerialName("consByteString-cpu-arguments-intercept")
    val consByteStringCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("consByteString-cpu-arguments-slope")
    val consByteStringCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("consByteString-memory-arguments-intercept")
    val consByteStringMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("consByteString-memory-arguments-slope")
    val consByteStringMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("constrData-cpu-arguments")
    val constrDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("constrData-memory-arguments")
    val constrDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("decodeUtf8-cpu-arguments-intercept")
    val decodeUtf8CpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("decodeUtf8-cpu-arguments-slope")
    val decodeUtf8CpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("decodeUtf8-memory-arguments-intercept")
    val decodeUtf8MemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("decodeUtf8-memory-arguments-slope")
    val decodeUtf8MemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("divideInteger-cpu-arguments-constant")
    val divideIntegerCpuArgumentsConstant: BigInteger,
    @Contextual
    @SerialName("divideInteger-cpu-arguments-model-arguments-intercept")
    val divideIntegerCpuArgumentsModelArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("divideInteger-cpu-arguments-model-arguments-slope")
    val divideIntegerCpuArgumentsModelArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("divideInteger-memory-arguments-intercept")
    val divideIntegerMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("divideInteger-memory-arguments-minimum")
    val divideIntegerMemoryArgumentsMinimum: BigInteger,
    @Contextual
    @SerialName("divideInteger-memory-arguments-slope")
    val divideIntegerMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("encodeUtf8-cpu-arguments-intercept")
    val encodeUtf8CpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("encodeUtf8-cpu-arguments-slope")
    val encodeUtf8CpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("encodeUtf8-memory-arguments-intercept")
    val encodeUtf8MemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("encodeUtf8-memory-arguments-slope")
    val encodeUtf8MemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("equalsByteString-cpu-arguments-constant")
    val equalsByteStringCpuArgumentsConstant: BigInteger,
    @Contextual
    @SerialName("equalsByteString-cpu-arguments-intercept")
    val equalsByteStringCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("equalsByteString-cpu-arguments-slope")
    val equalsByteStringCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("equalsByteString-memory-arguments")
    val equalsByteStringMemoryArguments: BigInteger,
    @Contextual
    @SerialName("equalsData-cpu-arguments-intercept")
    val equalsDataCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("equalsData-cpu-arguments-slope")
    val equalsDataCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("equalsData-memory-arguments")
    val equalsDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("equalsInteger-cpu-arguments-intercept")
    val equalsIntegerCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("equalsInteger-cpu-arguments-slope")
    val equalsIntegerCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("equalsInteger-memory-arguments")
    val equalsIntegerMemoryArguments: BigInteger,
    @Contextual
    @SerialName("equalsString-cpu-arguments-constant")
    val equalsStringCpuArgumentsConstant: BigInteger,
    @Contextual
    @SerialName("equalsString-cpu-arguments-intercept")
    val equalsStringCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("equalsString-cpu-arguments-slope")
    val equalsStringCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("equalsString-memory-arguments")
    val equalsStringMemoryArguments: BigInteger,
    @Contextual
    @SerialName("fstPair-cpu-arguments")
    val fstPairCpuArguments: BigInteger,
    @Contextual
    @SerialName("fstPair-memory-arguments")
    val fstPairMemoryArguments: BigInteger,
    @Contextual
    @SerialName("headList-cpu-arguments")
    val headListCpuArguments: BigInteger,
    @Contextual
    @SerialName("headList-memory-arguments")
    val headListMemoryArguments: BigInteger,
    @Contextual
    @SerialName("iData-cpu-arguments")
    val iDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("iData-memory-arguments")
    val iDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("ifThenElse-cpu-arguments")
    val ifThenElseCpuArguments: BigInteger,
    @Contextual
    @SerialName("ifThenElse-memory-arguments")
    val ifThenElseMemoryArguments: BigInteger,
    @Contextual
    @SerialName("indexByteString-cpu-arguments")
    val indexByteStringCpuArguments: BigInteger,
    @Contextual
    @SerialName("indexByteString-memory-arguments")
    val indexByteStringMemoryArguments: BigInteger,
    @Contextual
    @SerialName("lengthOfByteString-cpu-arguments")
    val lengthOfByteStringCpuArguments: BigInteger,
    @Contextual
    @SerialName("lengthOfByteString-memory-arguments")
    val lengthOfByteStringMemoryArguments: BigInteger,
    @Contextual
    @SerialName("lessThanByteString-cpu-arguments-intercept")
    val lessThanByteStringCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("lessThanByteString-cpu-arguments-slope")
    val lessThanByteStringCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("lessThanByteString-memory-arguments")
    val lessThanByteStringMemoryArguments: BigInteger,
    @Contextual
    @SerialName("lessThanEqualsByteString-cpu-arguments-intercept")
    val lessThanEqualsByteStringCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("lessThanEqualsByteString-cpu-arguments-slope")
    val lessThanEqualsByteStringCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("lessThanEqualsByteString-memory-arguments")
    val lessThanEqualsByteStringMemoryArguments: BigInteger,
    @Contextual
    @SerialName("lessThanEqualsInteger-cpu-arguments-intercept")
    val lessThanEqualsIntegerCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("lessThanEqualsInteger-cpu-arguments-slope")
    val lessThanEqualsIntegerCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("lessThanEqualsInteger-memory-arguments")
    val lessThanEqualsIntegerMemoryArguments: BigInteger,
    @Contextual
    @SerialName("lessThanInteger-cpu-arguments-intercept")
    val lessThanIntegerCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("lessThanInteger-cpu-arguments-slope")
    val lessThanIntegerCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("lessThanInteger-memory-arguments")
    val lessThanIntegerMemoryArguments: BigInteger,
    @Contextual
    @SerialName("listData-cpu-arguments")
    val listDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("listData-memory-arguments")
    val listDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("mapData-cpu-arguments")
    val mapDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("mapData-memory-arguments")
    val mapDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("mkCons-cpu-arguments")
    val mkConsCpuArguments: BigInteger,
    @Contextual
    @SerialName("mkCons-memory-arguments")
    val mkConsMemoryArguments: BigInteger,
    @Contextual
    @SerialName("mkNilData-cpu-arguments")
    val mkNilDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("mkNilData-memory-arguments")
    val mkNilDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("mkNilPairData-cpu-arguments")
    val mkNilPairDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("mkNilPairData-memory-arguments")
    val mkNilPairDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("mkPairData-cpu-arguments")
    val mkPairDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("mkPairData-memory-arguments")
    val mkPairDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("modInteger-cpu-arguments-constant")
    val modIntegerCpuArgumentsConstant: BigInteger,
    @Contextual
    @SerialName("modInteger-cpu-arguments-model-arguments-intercept")
    val modIntegerCpuArgumentsModelArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("modInteger-cpu-arguments-model-arguments-slope")
    val modIntegerCpuArgumentsModelArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("modInteger-memory-arguments-intercept")
    val modIntegerMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("modInteger-memory-arguments-minimum")
    val modIntegerMemoryArgumentsMinimum: BigInteger,
    @Contextual
    @SerialName("modInteger-memory-arguments-slope")
    val modIntegerMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("multiplyInteger-cpu-arguments-intercept")
    val multiplyIntegerCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("multiplyInteger-cpu-arguments-slope")
    val multiplyIntegerCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("multiplyInteger-memory-arguments-intercept")
    val multiplyIntegerMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("multiplyInteger-memory-arguments-slope")
    val multiplyIntegerMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("nullList-cpu-arguments")
    val nullListCpuArguments: BigInteger,
    @Contextual
    @SerialName("nullList-memory-arguments")
    val nullListMemoryArguments: BigInteger,
    @Contextual
    @SerialName("quotientInteger-cpu-arguments-constant")
    val quotientIntegerCpuArgumentsConstant: BigInteger,
    @Contextual
    @SerialName("quotientInteger-cpu-arguments-model-arguments-intercept")
    val quotientIntegerCpuArgumentsModelArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("quotientInteger-cpu-arguments-model-arguments-slope")
    val quotientIntegerCpuArgumentsModelArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("quotientInteger-memory-arguments-intercept")
    val quotientIntegerMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("quotientInteger-memory-arguments-minimum")
    val quotientIntegerMemoryArgumentsMinimum: BigInteger,
    @Contextual
    @SerialName("quotientInteger-memory-arguments-slope")
    val quotientIntegerMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("remainderInteger-cpu-arguments-constant")
    val remainderIntegerCpuArgumentsConstant: BigInteger,
    @Contextual
    @SerialName("remainderInteger-cpu-arguments-model-arguments-intercept")
    val remainderIntegerCpuArgumentsModelArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("remainderInteger-cpu-arguments-model-arguments-slope")
    val remainderIntegerCpuArgumentsModelArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("remainderInteger-memory-arguments-intercept")
    val remainderIntegerMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("remainderInteger-memory-arguments-minimum")
    val remainderIntegerMemoryArgumentsMinimum: BigInteger,
    @Contextual
    @SerialName("remainderInteger-memory-arguments-slope")
    val remainderIntegerMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("serialiseData-cpu-arguments-intercept")
    val serialiseDataCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("serialiseData-cpu-arguments-slope")
    val serialiseDataCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("serialiseData-memory-arguments-intercept")
    val serialiseDataMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("serialiseData-memory-arguments-slope")
    val serialiseDataMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("sha2_256-cpu-arguments-intercept")
    val sha2256CpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("sha2_256-cpu-arguments-slope")
    val sha2256CpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("sha2_256-memory-arguments")
    val sha2256MemoryArguments: BigInteger,
    @Contextual
    @SerialName("sha3_256-cpu-arguments-intercept")
    val sha3256CpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("sha3_256-cpu-arguments-slope")
    val sha3256CpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("sha3_256-memory-arguments")
    val sha3256MemoryArguments: BigInteger,
    @Contextual
    @SerialName("sliceByteString-cpu-arguments-intercept")
    val sliceByteStringCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("sliceByteString-cpu-arguments-slope")
    val sliceByteStringCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("sliceByteString-memory-arguments-intercept")
    val sliceByteStringMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("sliceByteString-memory-arguments-slope")
    val sliceByteStringMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("sndPair-cpu-arguments")
    val sndPairCpuArguments: BigInteger,
    @Contextual
    @SerialName("sndPair-memory-arguments")
    val sndPairMemoryArguments: BigInteger,
    @Contextual
    @SerialName("subtractInteger-cpu-arguments-intercept")
    val subtractIntegerCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("subtractInteger-cpu-arguments-slope")
    val subtractIntegerCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("subtractInteger-memory-arguments-intercept")
    val subtractIntegerMemoryArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("subtractInteger-memory-arguments-slope")
    val subtractIntegerMemoryArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("tailList-cpu-arguments")
    val tailListCpuArguments: BigInteger,
    @Contextual
    @SerialName("tailList-memory-arguments")
    val tailListMemoryArguments: BigInteger,
    @Contextual
    @SerialName("trace-cpu-arguments")
    val traceCpuArguments: BigInteger,
    @Contextual
    @SerialName("trace-memory-arguments")
    val traceMemoryArguments: BigInteger,
    @Contextual
    @SerialName("unBData-cpu-arguments")
    val unBDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("unBData-memory-arguments")
    val unBDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("unConstrData-cpu-arguments")
    val unConstrDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("unConstrData-memory-arguments")
    val unConstrDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("unIData-cpu-arguments")
    val unIDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("unIData-memory-arguments")
    val unIDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("unListData-cpu-arguments")
    val unListDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("unListData-memory-arguments")
    val unListDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("unMapData-cpu-arguments")
    val unMapDataCpuArguments: BigInteger,
    @Contextual
    @SerialName("unMapData-memory-arguments")
    val unMapDataMemoryArguments: BigInteger,
    @Contextual
    @SerialName("verifyEcdsaSecp256k1Signature-cpu-arguments")
    val verifyEcdsaSecp256k1SignatureCpuArguments: BigInteger,
    @Contextual
    @SerialName("verifyEcdsaSecp256k1Signature-memory-arguments")
    val verifyEcdsaSecp256k1SignatureMemoryArguments: BigInteger,
    @Contextual
    @SerialName("verifyEd25519Signature-cpu-arguments-intercept")
    val verifyEd25519SignatureCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("verifyEd25519Signature-cpu-arguments-slope")
    val verifyEd25519SignatureCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("verifyEd25519Signature-memory-arguments")
    val verifyEd25519SignatureMemoryArguments: BigInteger,
    @Contextual
    @SerialName("verifySchnorrSecp256k1Signature-cpu-arguments-intercept")
    val verifySchnorrSecp256k1SignatureCpuArgumentsIntercept: BigInteger,
    @Contextual
    @SerialName("verifySchnorrSecp256k1Signature-cpu-arguments-slope")
    val verifySchnorrSecp256k1SignatureCpuArgumentsSlope: BigInteger,
    @Contextual
    @SerialName("verifySchnorrSecp256k1Signature-memory-arguments")
    val verifySchnorrSecp256k1SignatureMemoryArguments: Int
)
