package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.BytesSize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Transaction failed because it exceeds the maximum size allowed by the protocol. Indeed, once serialized, transactions must be under a bytes limit specified by a protocol parameter. The field 'data.measuredTransactionSize' indicates the actual measured size of your serialized transaction, whereas 'data.maximumTransactionSize' indicates the current maximum size enforced by the ledger.
 */
@Serializable
@SerialName("3119")
data class TransactionTooLargeFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: TransactionTooLargeFaultData,
) : Fault

@Serializable
data class TransactionTooLargeFaultData(
    @SerialName("measuredTransactionSize")
    val measuredTransactionSize: BytesSize,
    @SerialName("maximumTransactionSize")
    val maximumTransactionSize: BytesSize,
) : FaultData

// { "title": "SubmitTransactionFailure<TransactionTooLarge>"
// , "description": "The transaction exceeds the maximum size allowed by the protocol. Indeed, once serialized, transactions must be under a bytes limit specified by a protocol parameter. The field 'data.measuredTransactionSize' indicates the actual measured size of your serialized transaction, whereas 'data.maximumTransactionSize' indicates the current maximum size enforced by the ledger."
// , "type": "object"
// , "required": [ "code", "message", "data" ]
// , "additionalProperties": false
// , "properties":
//  { "code": { "type": "integer", "enum": [ 3119 ] }
//  , "message": { "type": "string" }
//  , "data":
//  { "type": "object"
//  , "additionalProperties": false
//  , "required": [ "measuredTransactionSize", "maximumTransactionSize" ]
//  , "properties":
//  { "measuredTransactionSize": { "$ref": "cardano.json#/definitions/NumberOfBytes" }
//  , "maximumTransactionSize": { "$ref": "cardano.json#/definitions/NumberOfBytes" }
//  }
//  }
//  }
// }
