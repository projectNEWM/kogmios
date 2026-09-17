package io.newm.kogmios.serializers

import com.fasterxml.jackson.core.exc.StreamConstraintsException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.JsonNode
import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.protocols.messages.Cbor
import io.newm.kogmios.protocols.messages.JsonRpcErrorResponse
import io.newm.kogmios.protocols.messages.JsonRpcResponse
import io.newm.kogmios.protocols.messages.MsgAcquire
import io.newm.kogmios.protocols.messages.MsgFindIntersect
import io.newm.kogmios.protocols.messages.MsgNextBlockResponse
import io.newm.kogmios.protocols.messages.MsgNextTransaction
import io.newm.kogmios.protocols.messages.MsgQuery
import io.newm.kogmios.protocols.messages.MsgQueryBlockHeightResponse
import io.newm.kogmios.protocols.messages.MsgQueryProjectedRewardsResponse
import io.newm.kogmios.protocols.messages.MsgQueryUtxoResponse
import io.newm.kogmios.protocols.messages.MsgSubmitTx
import io.newm.kogmios.protocols.messages.SubmitOrEvalTx
import io.newm.kogmios.protocols.model.Asset
import io.newm.kogmios.protocols.model.Certificate
import io.newm.kogmios.protocols.model.DelegateRepresentative
import io.newm.kogmios.protocols.model.DelegateRepresentativeAbstain
import io.newm.kogmios.protocols.model.ExecutionPrices
import io.newm.kogmios.protocols.model.result.GenesisConfigResult
import io.newm.kogmios.protocols.model.GovernanceAction
import io.newm.kogmios.protocols.model.GovernanceVoter
import io.newm.kogmios.protocols.model.GovernanceVoterGenesisDelegate
import io.newm.kogmios.protocols.model.InformationGovernanceAction
import io.newm.kogmios.protocols.model.MetadataBytes
import io.newm.kogmios.protocols.model.MetadataInteger
import io.newm.kogmios.protocols.model.MetadataList
import io.newm.kogmios.protocols.model.MetadataMap
import io.newm.kogmios.protocols.model.MetadataString
import io.newm.kogmios.protocols.model.Origin
import io.newm.kogmios.protocols.model.OriginString
import io.newm.kogmios.protocols.model.Script
import io.newm.kogmios.protocols.model.ScriptNative
import io.newm.kogmios.protocols.model.ScriptPlutusV1
import io.newm.kogmios.protocols.model.ScriptPlutusV2
import io.newm.kogmios.protocols.model.ScriptPlutusV3
import io.newm.kogmios.protocols.model.ScriptPurpose
import io.newm.kogmios.protocols.model.ScriptPurposeSpend
import io.newm.kogmios.protocols.model.StakeDelegationCertificate
import io.newm.kogmios.protocols.model.TransactionMetadata
import io.newm.kogmios.protocols.model.TxShelleyBootstrap
import io.newm.kogmios.protocols.model.UtxoOutputValue
import io.newm.kogmios.protocols.model.Voter
import io.newm.kogmios.protocols.model.result.AlonzoGenesisConfigResult
import io.newm.kogmios.protocols.model.result.HealthResult
import io.newm.kogmios.protocols.model.result.InstantResult
import io.newm.kogmios.protocols.model.result.RollForward
import io.newm.kogmios.protocols.model.fault.Fault
import io.newm.kogmios.protocols.model.fault.InternalErrorFault
import io.newm.kogmios.protocols.model.fault.ScriptExecutionFailureFault
import java.math.BigInteger
import kotlin.time.Instant
import org.apache.commons.numbers.fraction.BigFraction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OgmiosJsonTest {
    private val mapper = OgmiosJson.mapper

    @Test
    fun `deep native scripts survive response decoding logging and encoding`() {
        val depth = 5_383
        val nativeScript = deepNativeScript(depth)
        val json =
            buildString {
                append("""{"jsonrpc":"2.0","result":{"tip":{"slot":133883340,"id":"tip","height":5183974},"block":{"era":"conway","id":"block","ancestor":"parent","height":5183974,"slot":133883340,"size":{"bytes":1},"transactions":[{"id":"tx","spends":"inputs","inputs":[],"outputs":[{"address":"addr","value":{"ada":{"lovelace":0}},"script":{"language":"native","json":""")
                append(nativeScript)
                append("""}}],"fee":{"ada":{"lovelace":0}},"signatories":[],"scripts":{"script":{"language":"native","json":""")
                append(nativeScript)
                append("""}}}],"protocol":{"version":{"major":8,"minor":0}},"issuer":{"verificationKey":"vk","vrfVerificationKey":"vrf","operationalCertificate":{"count":1,"kes":{"period":1,"verificationKey":"kes"}},"leaderValue":{"output":"out","proof":"proof"}},"type":"praos"},"direction":"forward"},"method":"nextBlock","id":"id"}""")
            }

        val response = mapper.readValue(json, JsonRpcResponse::class.java) as MsgNextBlockResponse
        val result = response.result as RollForward
        val transaction = result.block
            .let { it as io.newm.kogmios.protocols.model.BlockPraos }
            .transactions
            .single()
        val witness = transaction.scripts!!.getValue("script") as ScriptNative
        val output = transaction.outputs.single().script as ScriptNative

        assertDeepSignature(witness.json, depth)
        assertDeepSignature(output.json, depth)
        response.toString()

        val roundTrip = mapper.readValue(mapper.writeValueAsString(response), JsonRpcResponse::class.java) as MsgNextBlockResponse
        val roundTripScript =
            ((roundTrip.result as RollForward).block as io.newm.kogmios.protocols.model.BlockPraos)
                .transactions
                .single()
                .scripts!!
                .getValue("script") as ScriptNative
        assertDeepSignature(roundTripScript.json, depth)
    }

    @Test
    fun `parsing beyond configured depth fails without overflowing the stack`() {
        val input = "[".repeat(16_385) + "0" + "]".repeat(16_385)

        val exception = assertThrows<StreamConstraintsException> { mapper.readTree(input) }

        assertThat(exception).hasMessageThat().contains("16384")
    }

    @Test
    fun `requests preserve their JSON wire contracts`() {
        val requests =
            listOf(
                MsgFindIntersect(
                    params = io.newm.kogmios.protocols.model
                        .FindIntersect(listOf(OriginString())),
                    id = "find"
                ),
                MsgAcquire(params = Origin(), id = "acquire"),
                MsgQuery(method = MsgQuery.METHOD_QUERY_NETWORK_TIP, params = null, id = "query"),
                MsgNextTransaction(id = "next"),
                MsgSubmitTx(params = SubmitOrEvalTx(Cbor("deadbeef")), id = "submit"),
            ).associateBy { it.id }.mapValues { mapper.valueToTree<JsonNode>(it.value) }

        assertThat(
            requests
                .getValue("find")
                .path("params")
                .path("points")[0]
                .textValue()
        ).isEqualTo("origin")
        assertThat(
            requests
                .getValue("acquire")
                .path("params")
                .path("point")
                .textValue()
        ).isEqualTo("origin")
        assertThat(requests.getValue("query").has("params")).isTrue()
        assertThat(requests.getValue("query").path("params").isNull).isTrue()
        assertThat(
            requests
                .getValue("next")
                .path("params")
                .path("fields")
                .textValue()
        ).isEqualTo("all")
        assertThat(
            requests
                .getValue("submit")
                .path("params")
                .path("transaction")
                .path("cbor")
                .textValue()
        ).isEqualTo("deadbeef")
        requests.values.forEach {
            assertThat(it.has("type")).isFalse()
            assertThat(it.has("completableDeferred")).isFalse()
            assertThat(it.has("cause")).isFalse()
            assertThat(it.path("jsonrpc").textValue()).isEqualTo("2.0")
        }
    }

    @Test
    fun `responses and discriminated models dispatch by visible and virtual tags`() {
        val success =
            mapper.readValue(
                """{"jsonrpc":"2.0","result":42,"id":"ok","method":"queryNetwork/blockHeight"}""",
                JsonRpcResponse::class.java,
            )
        assertThat(success).isInstanceOf(MsgQueryBlockHeightResponse::class.java)
        assertThat((success as MsgQueryBlockHeightResponse).result.value).isEqualTo(42)

        val error =
            mapper.readValue(
                """{"jsonrpc":"2.0","result":42,"error":{"message":"bad","data":"raw","code":-1},"id":"error","method":"queryNetwork/blockHeight"}""",
                JsonRpcResponse::class.java,
            )
        assertThat(error).isInstanceOf(JsonRpcErrorResponse::class.java)
        assertThat((error as JsonRpcErrorResponse).error).isInstanceOf(InternalErrorFault::class.java)

        val nestedFault =
            mapper.readValue(
                """{"message":"failed","data":[{"validator":{"index":0,"purpose":"spend"},"error":{"message":"inner","data":"raw","code":-1}}],"code":3010}""",
                Fault::class.java,
            )
        assertThat(nestedFault).isInstanceOf(ScriptExecutionFailureFault::class.java)
        assertThat((nestedFault as ScriptExecutionFailureFault).data.single().error).isInstanceOf(InternalErrorFault::class.java)

        assertThrows<JsonMappingException> {
            mapper.readValue("""{"result":1,"method":"unknown","id":"x"}""", JsonRpcResponse::class.java)
        }
        assertThrows<JsonMappingException> {
            mapper.readValue("""{"type":"unknown"}""", io.newm.kogmios.protocols.model.Block::class.java)
        }

        val voter = mapper.readValue("""{"id":"voter","role":"genesisDelegate"}""", GovernanceVoter::class.java)
        assertThat(voter).isInstanceOf(GovernanceVoterGenesisDelegate::class.java)
        assertThat((voter as GovernanceVoterGenesisDelegate).role).isEqualTo("genesisDelegate")
        val voterJson = mapper.valueToTree<JsonNode>(voter)
        assertThat(voterJson.fieldNames().asSequence().count { it == "role" }).isEqualTo(1)

        val virtualVoter = mapper.readValue("""{"id":"voter","role":"stakePoolOperator"}""", Voter::class.java)
        assertThat(
            mapper
                .valueToTree<JsonNode>(virtualVoter)
                .fieldNames()
                .asSequence()
                .count { it == "role" }
        ).isEqualTo(1)

        val purpose =
            mapper.readValue(
                """{"outputReference":{"transaction":{"id":"tx"},"index":0},"purpose":"spend"}""",
                ScriptPurpose::class.java,
            )
        assertThat(purpose).isInstanceOf(ScriptPurposeSpend::class.java)
        assertThat((purpose as ScriptPurposeSpend).purpose).isEqualTo("spend")

        val genesis =
            mapper.readValue(
                """{"updatableParameters":{"minUtxoDepositCoefficient":1,"collateralPercentage":2,"plutusCostModels":{},"maxCollateralInputs":3,"maxExecutionUnitsPerBlock":{"memory":4,"cpu":5},"maxExecutionUnitsPerTransaction":{"memory":6,"cpu":7},"maxValueSize":{"bytes":8},"scriptExecutionPrices":{"memory":"1/2","cpu":"0.25"}},"era":"alonzo"}""",
                GenesisConfigResult::class.java,
            )
        assertThat(genesis).isInstanceOf(AlonzoGenesisConfigResult::class.java)
        assertThat((genesis as AlonzoGenesisConfigResult).era).isEqualTo("alonzo")

        assertThat(mapper.readValue("""{"type":"information"}""", GovernanceAction::class.java))
            .isSameInstanceAs(InformationGovernanceAction)
        assertThat(mapper.readValue("""{"from":"stake","credential":"cred","type":"stakeDelegation"}""", Certificate::class.java))
            .isInstanceOf(StakeDelegationCertificate::class.java)
        assertThat(mapper.readValue("""{"type":"abstain"}""", DelegateRepresentative::class.java))
            .isSameInstanceAs(DelegateRepresentativeAbstain)

        val scripts =
            listOf(
                """{"language":"native","json":{"clause":"signature","from":"key"}}""" to ScriptNative::class.java,
                """{"cbor":"01","language":"plutus:v1"}""" to ScriptPlutusV1::class.java,
                """{"language":"plutus:v2","cbor":"02"}""" to ScriptPlutusV2::class.java,
                """{"cbor":"03","language":"plutus:v3"}""" to ScriptPlutusV3::class.java,
            )
        scripts.forEach { (json, type) -> assertThat(mapper.readValue(json, Script::class.java)).isInstanceOf(type) }
    }

    @Test
    fun `transformed values scalars collections and required fields retain fidelity`() {
        val projected =
            mapper.readValue(
                """{"result":{"stake1":{"pool1":{"ada":{"lovelace":7}}}},"method":"queryLedgerState/projectedRewards","id":"projected"}""",
                JsonRpcResponse::class.java,
            ) as MsgQueryProjectedRewardsResponse
        assertThat(
            projected.result
                .getValue("stake1")
                .getValue("pool1")
                .ada.lovelace
        ).isEqualTo(BigInteger.valueOf(7))

        val huge = BigInteger("922337203685477580812345")
        val utxo =
            mapper.readValue(
                """{"result":[{"transaction":{"id":"tx"},"index":0,"address":"addr","value":{"ada":{"lovelace":1},"policy":{"":$huge}},"script":{"language":"plutus:v1","cbor":"01"}}],"method":"queryLedgerState/utxo","id":"utxo"}""",
                JsonRpcResponse::class.java,
            ) as MsgQueryUtxoResponse
        assertThat(
            utxo.result
                .single()
                .value.assets!!
                .single()
        ).isEqualTo(Asset("policy", "", huge))
        assertThat(utxo.result.single().script).isInstanceOf(ScriptPlutusV1::class.java)

        val valueJson = mapper.writeValueAsString(utxo.result.single().value)
        val valueTree = mapper.readTree(valueJson)
        assertThat(valueTree.path("ada").path("lovelace").bigIntegerValue()).isEqualTo(BigInteger.ONE)
        assertThat(valueTree.path("policy").path("").bigIntegerValue()).isEqualTo(huge)
        assertThat(valueTree.has("assets")).isFalse()
        val valueRoundTrip = mapper.readValue(valueJson, UtxoOutputValue::class.java)
        assertThat(valueRoundTrip.assets!!.single().quantity).isEqualTo(huge)

        assertThat(mapper.readValue("\"3/4\"", BigFraction::class.java)).isEqualTo(BigFraction.of(3, 4))
        assertThat(mapper.readValue("\"0.75\"", BigFraction::class.java)).isEqualTo(BigFraction.of(3, 4))
        assertThat(mapper.readValue("\"1E+3\"", BigFraction::class.java)).isEqualTo(BigFraction.of(1000))
        val prices = mapper.readValue("""{"memory":"1/3","cpu":"0.2"}""", ExecutionPrices::class.java)
        assertThat(prices.cpu).isEqualTo(BigFraction.of(1, 5))

        val instant = mapper.readValue("\"2024-01-02T03:04:05Z\"", InstantResult::class.java)
        assertThat(instant.value).isEqualTo(Instant.parse("2024-01-02T03:04:05Z"))
        assertThat(mapper.writeValueAsString(instant)).isEqualTo("\"2024-01-02T03:04:05Z\"")

        val health =
            mapper.readValue(
                """{"connectionStatus":"connected","currentEpoch":1,"currentEra":"conway","lastKnownTip":{"height":2,"id":"tip","slot":3},"lastTipUpdate":"now","metrics":{"activeConnections":1,"runtimeStats":{"cpuTime":1,"currentHeapSize":2,"gcCpuTime":3,"maxHeapSize":4},"sessionDurations":{"max":1,"mean":1,"min":1},"totalConnections":1,"totalMessages":2,"totalUnrouted":0},"network":"preprod","networkSynchronization":1.0,"slotInEpoch":4,"startTime":"then","version":"1"}""",
                HealthResult::class.java,
            )
        assertThat(health.currentEra.name).isEqualTo("CONWAY")
        assertThat(mapper.writeValueAsString(health)).contains("\"currentEra\":\"CONWAY\"")

        val nullable = mapper.readValue("""{"signature":"s","key":"k","chainCode":null,"addressAttributes":null}""", TxShelleyBootstrap::class.java)
        assertThat(nullable.chainCode).isNull()
        assertThrows<JsonMappingException> {
            mapper.readValue("""{"signature":"s","key":"k","addressAttributes":null}""", TxShelleyBootstrap::class.java)
        }
    }

    @Test
    fun `metadata tagged values round trip with nested exact integers`() {
        val json =
            """{"hash":"hash","labels":{"674":{"json":{"map":[{"k":{"string":"msg"},"v":{"list":[{"string":"hello"},{"int":42}]}},{"k":{"bytes":"deadbeef"},"v":{"map":[{"k":{"string":"favorite_number"},"v":{"int":79223372036854775807}}]}}]}}},"scripts":[]}"""
        val metadata = mapper.readValue(json, TransactionMetadata::class.java)
        val root = metadata.labels.getValue("674").json as MetadataMap
        val list = root[MetadataString("msg")] as MetadataList
        assertThat((list[0] as MetadataString).string).isEqualTo("hello")
        assertThat((list[1] as MetadataInteger).int).isEqualTo(BigInteger.valueOf(42))
        val nested = root[MetadataBytes("deadbeef")] as MetadataMap
        assertThat((nested[MetadataString("favorite_number")] as MetadataInteger).int)
            .isEqualTo(BigInteger("79223372036854775807"))

        val roundTrip = mapper.readValue(mapper.writeValueAsString(metadata), TransactionMetadata::class.java)
        val roundTripRoot = roundTrip.labels.getValue("674").json as MetadataMap
        assertThat(((roundTripRoot[MetadataBytes("deadbeef")] as MetadataMap)[MetadataString("favorite_number")] as MetadataInteger).int)
            .isEqualTo(BigInteger("79223372036854775807"))
    }

    private fun deepNativeScript(depth: Int): String =
        buildString {
            repeat(depth) { append("""{"clause":"all","from":[""") }
            append("""{"clause":"signature","from":"ba386209c0f81f9570b6feb45cedc2649144440157677c720bfd314a"}""")
            repeat(depth) { append("]}") }
        }

    private fun assertDeepSignature(
        root: JsonNode,
        depth: Int
    ) {
        var node = root
        repeat(depth) {
            assertThat(node.path("clause").textValue()).isEqualTo("all")
            node = node.path("from")[0]
        }
        assertThat(node.path("clause").textValue()).isEqualTo("signature")
        assertThat(node.path("from").textValue())
            .isEqualTo("ba386209c0f81f9570b6feb45cedc2649144440157677c720bfd314a")
    }
}
