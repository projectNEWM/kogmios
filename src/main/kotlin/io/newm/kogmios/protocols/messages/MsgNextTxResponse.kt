package io.newm.kogmios.protocols.messages

import io.newm.kogmios.protocols.model.TxBabbage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response that comes back from Ogmios after a nextTx mempool message is sent.
 */
@Serializable
@SerialName("NextTx")
data class MsgNextTxResponse(
    @SerialName("result")
    val result: TxBabbage?,
    @SerialName("reflection")
    override val reflection: String,
) : JsonWspResponse()

// JSON Example
// {
//    "type": "jsonwsp/response",
//    "version": "1.0",
//    "servicename": "ogmios",
//    "methodname": "NextTx",
//    "result": {"witness":{"signatures":{"4499320a77997987955eadba91721d5be54ca36536c5448009e822ba3f882d69":"/KgWroAJb2VALAEAU8/zlf1k0km4UOrQwt0h0l4Vnf3TNeZfi1MIBnxhGFIFei9XXZGn6fml5loBUUKr1ARsBA=="},"scripts":{},"datums":{},"redeemers":{},"bootstrap":[]},"raw":"hKQAgYJYIEhuSHNDE8njmVY9NIFpYXHO+Z5kM+oh7gPjgydlQPOFAAGBogBYHWDaDrXtdhFILsUIm2nYcODFbBxFGAJWESOY4INbARsAAAA9cQ2NdQIaAAKY4QMaAOFHZaEAgYJYIESZMgp3mXmHlV6tupFyHVvlTKNlNsVEgAnoIro/iC1pWED8qBaugAlvZUAsAQBTz/OV/WTSSbhQ6tDC3SHSXhWd/dM15l+LUwgGfGEYUgV6L1ddkafp+aXmWgFRQqvUBGwE9fY=","id":"3840c8c658614b3e5462bb33845ac046991c75b992eab24378b96731f77cccf0","body":{"inputs":[{"txId":"486e48734313c9e399563d3481696171cef99e6433ea21ee03e383276540f385","index":0}],"collaterals":[],"references":[],"collateralReturn":null,"totalCollateral":null,"outputs":[{"address":"addr_test1vrdqad0dwcg5stk9pzdknkrsurzkc8z9rqp9vyfrnrsgxkc4r8za2","value":{"coins":263889718645,"assets":{}},"datumHash":null,"datum":null,"script":null}],"certificates":[],"withdrawals":{},"fee":170209,"validityInterval":{"invalidBefore":null,"invalidHereafter":14763877},"update":null,"mint":{"coins":0,"assets":{}},"network":null,"scriptIntegrityHash":null,"requiredExtraSignatures":[]},"metadata":null,"inputSource":"inputs"},
//    "reflection":"NextTx:5a7319ea-a230-415c-859b-78a60c2ce7d2"
// }
