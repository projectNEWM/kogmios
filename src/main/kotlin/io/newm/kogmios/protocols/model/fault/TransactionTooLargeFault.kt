package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.BytesSize

/**
 * Transaction failed because it exceeds the maximum size allowed by the protocol. Indeed, once serialized, transactions must be under a bytes limit specified by a protocol parameter. The field 'data.measuredTransactionSize' indicates the actual measured size of your serialized transaction, whereas 'data.maximumTransactionSize' indicates the current maximum size enforced by the ledger.
 */

@JsonTypeName("3119")
data class TransactionTooLargeFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: TransactionTooLargeFaultData,
) : Fault

data class TransactionTooLargeFaultData(
    @param:JsonProperty(value = "measuredTransactionSize", required = true)
    @get:JsonProperty("measuredTransactionSize")
    val measuredTransactionSize: BytesSize,
    @param:JsonProperty(value = "maximumTransactionSize", required = true)
    @get:JsonProperty("maximumTransactionSize")
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
