package io.projectnewm.kogmios.protocols.localstatequery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlutusV1(
    @SerialName("addInteger-cpu-arguments-intercept")
    val addIntegerCpuArgumentsIntercept: Int,
    @SerialName("addInteger-cpu-arguments-slope")
    val addIntegerCpuArgumentsSlope: Int,
    @SerialName("addInteger-memory-arguments-intercept")
    val addIntegerMemoryArgumentsIntercept: Int,
    @SerialName("addInteger-memory-arguments-slope")
    val addIntegerMemoryArgumentsSlope: Int,
    @SerialName("appendByteString-cpu-arguments-intercept")
    val appendByteStringCpuArgumentsIntercept: Int,
    @SerialName("appendByteString-cpu-arguments-slope")
    val appendByteStringCpuArgumentsSlope: Int,
    @SerialName("appendByteString-memory-arguments-intercept")
    val appendByteStringMemoryArgumentsIntercept: Int,
    @SerialName("appendByteString-memory-arguments-slope")
    val appendByteStringMemoryArgumentsSlope: Int,
    @SerialName("appendString-cpu-arguments-intercept")
    val appendStringCpuArgumentsIntercept: Int,
    @SerialName("appendString-cpu-arguments-slope")
    val appendStringCpuArgumentsSlope: Int,
    @SerialName("appendString-memory-arguments-intercept")
    val appendStringMemoryArgumentsIntercept: Int,
    @SerialName("appendString-memory-arguments-slope")
    val appendStringMemoryArgumentsSlope: Int,
    @SerialName("bData-cpu-arguments")
    val bDataCpuArguments: Int,
    @SerialName("bData-memory-arguments")
    val bDataMemoryArguments: Int,
    @SerialName("blake2b-cpu-arguments-intercept")
    val blake2bCpuArgumentsIntercept: Int,
    @SerialName("blake2b-cpu-arguments-slope")
    val blake2bCpuArgumentsSlope: Int,
    @SerialName("blake2b-memory-arguments")
    val blake2bMemoryArguments: Int,
    @SerialName("cekApplyCost-exBudgetCPU")
    val cekApplyCostExBudgetCPU: Int,
    @SerialName("cekApplyCost-exBudgetMemory")
    val cekApplyCostExBudgetMemory: Int,
    @SerialName("cekBuiltinCost-exBudgetCPU")
    val cekBuiltinCostExBudgetCPU: Int,
    @SerialName("cekBuiltinCost-exBudgetMemory")
    val cekBuiltinCostExBudgetMemory: Int,
    @SerialName("cekConstCost-exBudgetCPU")
    val cekConstCostExBudgetCPU: Int,
    @SerialName("cekConstCost-exBudgetMemory")
    val cekConstCostExBudgetMemory: Int,
    @SerialName("cekDelayCost-exBudgetCPU")
    val cekDelayCostExBudgetCPU: Int,
    @SerialName("cekDelayCost-exBudgetMemory")
    val cekDelayCostExBudgetMemory: Int,
    @SerialName("cekForceCost-exBudgetCPU")
    val cekForceCostExBudgetCPU: Int,
    @SerialName("cekForceCost-exBudgetMemory")
    val cekForceCostExBudgetMemory: Int,
    @SerialName("cekLamCost-exBudgetCPU")
    val cekLamCostExBudgetCPU: Int,
    @SerialName("cekLamCost-exBudgetMemory")
    val cekLamCostExBudgetMemory: Int,
    @SerialName("cekStartupCost-exBudgetCPU")
    val cekStartupCostExBudgetCPU: Int,
    @SerialName("cekStartupCost-exBudgetMemory")
    val cekStartupCostExBudgetMemory: Int,
    @SerialName("cekVarCost-exBudgetCPU")
    val cekVarCostExBudgetCPU: Int,
    @SerialName("cekVarCost-exBudgetMemory")
    val cekVarCostExBudgetMemory: Int,
    @SerialName("chooseData-cpu-arguments")
    val chooseDataCpuArguments: Int,
    @SerialName("chooseData-memory-arguments")
    val chooseDataMemoryArguments: Int,
    @SerialName("chooseList-cpu-arguments")
    val chooseListCpuArguments: Int,
    @SerialName("chooseList-memory-arguments")
    val chooseListMemoryArguments: Int,
    @SerialName("chooseUnit-cpu-arguments")
    val chooseUnitCpuArguments: Int,
    @SerialName("chooseUnit-memory-arguments")
    val chooseUnitMemoryArguments: Int,
    @SerialName("consByteString-cpu-arguments-intercept")
    val consByteStringCpuArgumentsIntercept: Int,
    @SerialName("consByteString-cpu-arguments-slope")
    val consByteStringCpuArgumentsSlope: Int,
    @SerialName("consByteString-memory-arguments-intercept")
    val consByteStringMemoryArgumentsIntercept: Int,
    @SerialName("consByteString-memory-arguments-slope")
    val consByteStringMemoryArgumentsSlope: Int,
    @SerialName("constrData-cpu-arguments")
    val constrDataCpuArguments: Int,
    @SerialName("constrData-memory-arguments")
    val constrDataMemoryArguments: Int,
    @SerialName("decodeUtf8-cpu-arguments-intercept")
    val decodeUtf8CpuArgumentsIntercept: Int,
    @SerialName("decodeUtf8-cpu-arguments-slope")
    val decodeUtf8CpuArgumentsSlope: Int,
    @SerialName("decodeUtf8-memory-arguments-intercept")
    val decodeUtf8MemoryArgumentsIntercept: Int,
    @SerialName("decodeUtf8-memory-arguments-slope")
    val decodeUtf8MemoryArgumentsSlope: Int,
    @SerialName("divideInteger-cpu-arguments-constant")
    val divideIntegerCpuArgumentsConstant: Int,
    @SerialName("divideInteger-cpu-arguments-model-arguments-intercept")
    val divideIntegerCpuArgumentsModelArgumentsIntercept: Int,
    @SerialName("divideInteger-cpu-arguments-model-arguments-slope")
    val divideIntegerCpuArgumentsModelArgumentsSlope: Int,
    @SerialName("divideInteger-memory-arguments-intercept")
    val divideIntegerMemoryArgumentsIntercept: Int,
    @SerialName("divideInteger-memory-arguments-minimum")
    val divideIntegerMemoryArgumentsMinimum: Int,
    @SerialName("divideInteger-memory-arguments-slope")
    val divideIntegerMemoryArgumentsSlope: Int,
    @SerialName("encodeUtf8-cpu-arguments-intercept")
    val encodeUtf8CpuArgumentsIntercept: Int,
    @SerialName("encodeUtf8-cpu-arguments-slope")
    val encodeUtf8CpuArgumentsSlope: Int,
    @SerialName("encodeUtf8-memory-arguments-intercept")
    val encodeUtf8MemoryArgumentsIntercept: Int,
    @SerialName("encodeUtf8-memory-arguments-slope")
    val encodeUtf8MemoryArgumentsSlope: Int,
    @SerialName("equalsByteString-cpu-arguments-constant")
    val equalsByteStringCpuArgumentsConstant: Int,
    @SerialName("equalsByteString-cpu-arguments-intercept")
    val equalsByteStringCpuArgumentsIntercept: Int,
    @SerialName("equalsByteString-cpu-arguments-slope")
    val equalsByteStringCpuArgumentsSlope: Int,
    @SerialName("equalsByteString-memory-arguments")
    val equalsByteStringMemoryArguments: Int,
    @SerialName("equalsData-cpu-arguments-intercept")
    val equalsDataCpuArgumentsIntercept: Int,
    @SerialName("equalsData-cpu-arguments-slope")
    val equalsDataCpuArgumentsSlope: Int,
    @SerialName("equalsData-memory-arguments")
    val equalsDataMemoryArguments: Int,
    @SerialName("equalsInteger-cpu-arguments-intercept")
    val equalsIntegerCpuArgumentsIntercept: Int,
    @SerialName("equalsInteger-cpu-arguments-slope")
    val equalsIntegerCpuArgumentsSlope: Int,
    @SerialName("equalsInteger-memory-arguments")
    val equalsIntegerMemoryArguments: Int,
    @SerialName("equalsString-cpu-arguments-constant")
    val equalsStringCpuArgumentsConstant: Int,
    @SerialName("equalsString-cpu-arguments-intercept")
    val equalsStringCpuArgumentsIntercept: Int,
    @SerialName("equalsString-cpu-arguments-slope")
    val equalsStringCpuArgumentsSlope: Int,
    @SerialName("equalsString-memory-arguments")
    val equalsStringMemoryArguments: Int,
    @SerialName("fstPair-cpu-arguments")
    val fstPairCpuArguments: Int,
    @SerialName("fstPair-memory-arguments")
    val fstPairMemoryArguments: Int,
    @SerialName("headList-cpu-arguments")
    val headListCpuArguments: Int,
    @SerialName("headList-memory-arguments")
    val headListMemoryArguments: Int,
    @SerialName("iData-cpu-arguments")
    val iDataCpuArguments: Int,
    @SerialName("iData-memory-arguments")
    val iDataMemoryArguments: Int,
    @SerialName("ifThenElse-cpu-arguments")
    val ifThenElseCpuArguments: Int,
    @SerialName("ifThenElse-memory-arguments")
    val ifThenElseMemoryArguments: Int,
    @SerialName("indexByteString-cpu-arguments")
    val indexByteStringCpuArguments: Int,
    @SerialName("indexByteString-memory-arguments")
    val indexByteStringMemoryArguments: Int,
    @SerialName("lengthOfByteString-cpu-arguments")
    val lengthOfByteStringCpuArguments: Int,
    @SerialName("lengthOfByteString-memory-arguments")
    val lengthOfByteStringMemoryArguments: Int,
    @SerialName("lessThanByteString-cpu-arguments-intercept")
    val lessThanByteStringCpuArgumentsIntercept: Int,
    @SerialName("lessThanByteString-cpu-arguments-slope")
    val lessThanByteStringCpuArgumentsSlope: Int,
    @SerialName("lessThanByteString-memory-arguments")
    val lessThanByteStringMemoryArguments: Int,
    @SerialName("lessThanEqualsByteString-cpu-arguments-intercept")
    val lessThanEqualsByteStringCpuArgumentsIntercept: Int,
    @SerialName("lessThanEqualsByteString-cpu-arguments-slope")
    val lessThanEqualsByteStringCpuArgumentsSlope: Int,
    @SerialName("lessThanEqualsByteString-memory-arguments")
    val lessThanEqualsByteStringMemoryArguments: Int,
    @SerialName("lessThanEqualsInteger-cpu-arguments-intercept")
    val lessThanEqualsIntegerCpuArgumentsIntercept: Int,
    @SerialName("lessThanEqualsInteger-cpu-arguments-slope")
    val lessThanEqualsIntegerCpuArgumentsSlope: Int,
    @SerialName("lessThanEqualsInteger-memory-arguments")
    val lessThanEqualsIntegerMemoryArguments: Int,
    @SerialName("lessThanInteger-cpu-arguments-intercept")
    val lessThanIntegerCpuArgumentsIntercept: Int,
    @SerialName("lessThanInteger-cpu-arguments-slope")
    val lessThanIntegerCpuArgumentsSlope: Int,
    @SerialName("lessThanInteger-memory-arguments")
    val lessThanIntegerMemoryArguments: Int,
    @SerialName("listData-cpu-arguments")
    val listDataCpuArguments: Int,
    @SerialName("listData-memory-arguments")
    val listDataMemoryArguments: Int,
    @SerialName("mapData-cpu-arguments")
    val mapDataCpuArguments: Int,
    @SerialName("mapData-memory-arguments")
    val mapDataMemoryArguments: Int,
    @SerialName("mkCons-cpu-arguments")
    val mkConsCpuArguments: Int,
    @SerialName("mkCons-memory-arguments")
    val mkConsMemoryArguments: Int,
    @SerialName("mkNilData-cpu-arguments")
    val mkNilDataCpuArguments: Int,
    @SerialName("mkNilData-memory-arguments")
    val mkNilDataMemoryArguments: Int,
    @SerialName("mkNilPairData-cpu-arguments")
    val mkNilPairDataCpuArguments: Int,
    @SerialName("mkNilPairData-memory-arguments")
    val mkNilPairDataMemoryArguments: Int,
    @SerialName("mkPairData-cpu-arguments")
    val mkPairDataCpuArguments: Int,
    @SerialName("mkPairData-memory-arguments")
    val mkPairDataMemoryArguments: Int,
    @SerialName("modInteger-cpu-arguments-constant")
    val modIntegerCpuArgumentsConstant: Int,
    @SerialName("modInteger-cpu-arguments-model-arguments-intercept")
    val modIntegerCpuArgumentsModelArgumentsIntercept: Int,
    @SerialName("modInteger-cpu-arguments-model-arguments-slope")
    val modIntegerCpuArgumentsModelArgumentsSlope: Int,
    @SerialName("modInteger-memory-arguments-intercept")
    val modIntegerMemoryArgumentsIntercept: Int,
    @SerialName("modInteger-memory-arguments-minimum")
    val modIntegerMemoryArgumentsMinimum: Int,
    @SerialName("modInteger-memory-arguments-slope")
    val modIntegerMemoryArgumentsSlope: Int,
    @SerialName("multiplyInteger-cpu-arguments-intercept")
    val multiplyIntegerCpuArgumentsIntercept: Int,
    @SerialName("multiplyInteger-cpu-arguments-slope")
    val multiplyIntegerCpuArgumentsSlope: Int,
    @SerialName("multiplyInteger-memory-arguments-intercept")
    val multiplyIntegerMemoryArgumentsIntercept: Int,
    @SerialName("multiplyInteger-memory-arguments-slope")
    val multiplyIntegerMemoryArgumentsSlope: Int,
    @SerialName("nullList-cpu-arguments")
    val nullListCpuArguments: Int,
    @SerialName("nullList-memory-arguments")
    val nullListMemoryArguments: Int,
    @SerialName("quotientInteger-cpu-arguments-constant")
    val quotientIntegerCpuArgumentsConstant: Int,
    @SerialName("quotientInteger-cpu-arguments-model-arguments-intercept")
    val quotientIntegerCpuArgumentsModelArgumentsIntercept: Int,
    @SerialName("quotientInteger-cpu-arguments-model-arguments-slope")
    val quotientIntegerCpuArgumentsModelArgumentsSlope: Int,
    @SerialName("quotientInteger-memory-arguments-intercept")
    val quotientIntegerMemoryArgumentsIntercept: Int,
    @SerialName("quotientInteger-memory-arguments-minimum")
    val quotientIntegerMemoryArgumentsMinimum: Int,
    @SerialName("quotientInteger-memory-arguments-slope")
    val quotientIntegerMemoryArgumentsSlope: Int,
    @SerialName("remainderInteger-cpu-arguments-constant")
    val remainderIntegerCpuArgumentsConstant: Int,
    @SerialName("remainderInteger-cpu-arguments-model-arguments-intercept")
    val remainderIntegerCpuArgumentsModelArgumentsIntercept: Int,
    @SerialName("remainderInteger-cpu-arguments-model-arguments-slope")
    val remainderIntegerCpuArgumentsModelArgumentsSlope: Int,
    @SerialName("remainderInteger-memory-arguments-intercept")
    val remainderIntegerMemoryArgumentsIntercept: Int,
    @SerialName("remainderInteger-memory-arguments-minimum")
    val remainderIntegerMemoryArgumentsMinimum: Int,
    @SerialName("remainderInteger-memory-arguments-slope")
    val remainderIntegerMemoryArgumentsSlope: Int,
    @SerialName("sha2_256-cpu-arguments-intercept")
    val sha2256CpuArgumentsIntercept: Int,
    @SerialName("sha2_256-cpu-arguments-slope")
    val sha2256CpuArgumentsSlope: Int,
    @SerialName("sha2_256-memory-arguments")
    val sha2256MemoryArguments: Int,
    @SerialName("sha3_256-cpu-arguments-intercept")
    val sha3256CpuArgumentsIntercept: Int,
    @SerialName("sha3_256-cpu-arguments-slope")
    val sha3256CpuArgumentsSlope: Int,
    @SerialName("sha3_256-memory-arguments")
    val sha3256MemoryArguments: Int,
    @SerialName("sliceByteString-cpu-arguments-intercept")
    val sliceByteStringCpuArgumentsIntercept: Int,
    @SerialName("sliceByteString-cpu-arguments-slope")
    val sliceByteStringCpuArgumentsSlope: Int,
    @SerialName("sliceByteString-memory-arguments-intercept")
    val sliceByteStringMemoryArgumentsIntercept: Int,
    @SerialName("sliceByteString-memory-arguments-slope")
    val sliceByteStringMemoryArgumentsSlope: Int,
    @SerialName("sndPair-cpu-arguments")
    val sndPairCpuArguments: Int,
    @SerialName("sndPair-memory-arguments")
    val sndPairMemoryArguments: Int,
    @SerialName("subtractInteger-cpu-arguments-intercept")
    val subtractIntegerCpuArgumentsIntercept: Int,
    @SerialName("subtractInteger-cpu-arguments-slope")
    val subtractIntegerCpuArgumentsSlope: Int,
    @SerialName("subtractInteger-memory-arguments-intercept")
    val subtractIntegerMemoryArgumentsIntercept: Int,
    @SerialName("subtractInteger-memory-arguments-slope")
    val subtractIntegerMemoryArgumentsSlope: Int,
    @SerialName("tailList-cpu-arguments")
    val tailListCpuArguments: Int,
    @SerialName("tailList-memory-arguments")
    val tailListMemoryArguments: Int,
    @SerialName("trace-cpu-arguments")
    val traceCpuArguments: Int,
    @SerialName("trace-memory-arguments")
    val traceMemoryArguments: Int,
    @SerialName("unBData-cpu-arguments")
    val unBDataCpuArguments: Int,
    @SerialName("unBData-memory-arguments")
    val unBDataMemoryArguments: Int,
    @SerialName("unConstrData-cpu-arguments")
    val unConstrDataCpuArguments: Int,
    @SerialName("unConstrData-memory-arguments")
    val unConstrDataMemoryArguments: Int,
    @SerialName("unIData-cpu-arguments")
    val unIDataCpuArguments: Int,
    @SerialName("unIData-memory-arguments")
    val unIDataMemoryArguments: Int,
    @SerialName("unListData-cpu-arguments")
    val unListDataCpuArguments: Int,
    @SerialName("unListData-memory-arguments")
    val unListDataMemoryArguments: Int,
    @SerialName("unMapData-cpu-arguments")
    val unMapDataCpuArguments: Int,
    @SerialName("unMapData-memory-arguments")
    val unMapDataMemoryArguments: Int,
    @SerialName("verifySignature-cpu-arguments-intercept")
    val verifySignatureCpuArgumentsIntercept: Int,
    @SerialName("verifySignature-cpu-arguments-slope")
    val verifySignatureCpuArgumentsSlope: Int,
    @SerialName("verifySignature-memory-arguments")
    val verifySignatureMemoryArguments: Int
)
