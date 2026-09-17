package io.newm.kogmios.protocols.model.fault

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonTypeName

import io.newm.kogmios.protocols.model.Ada

/**
 * The transaction is trying to withdraw more funds than specified in a governance action! The field 'data.providedWithdrawal' indicates the amount specified in the transaction, whereas 'data.computedWithdrawal' is the actual amount as computed by the ledger.
 */

@JsonTypeName("3158")
data class TreasuryWithdrawalMismatchFault(
    @param:JsonProperty(value = "code", required = true)
    @get:JsonProperty("code")
    override val code: Long,
    @param:JsonProperty(value = "message", required = true)
    @get:JsonProperty("message")
    override val message: String,
    @param:JsonProperty(value = "data", required = true)
    @get:JsonProperty("data")
    override val data: TreasuryWithdrawalMismatchFaultData,
) : Fault

data class TreasuryWithdrawalMismatchFaultData(
    @param:JsonProperty(value = "providedWithdrawal", required = true)
    @get:JsonProperty("providedWithdrawal")
    val providedWithdrawal: Ada,
    @param:JsonProperty(value = "computedWithdrawal", required = true)
    @get:JsonProperty("computedWithdrawal")
    val computedWithdrawal: Ada,
) : FaultData

// {
//          "title": "SubmitTransactionFailure<TreasuryWithdrawalMismatch>",
//          "description": "The transaction is trying to withdraw more funds than specified in a governance action! The field 'data.providedWithdrawal' indicates the amount specified in the transaction, whereas 'data.computedWithdrawal' is the actual amount as computed by the ledger.",
//          "type": "object",
//          "required":
//          [
//            "code",
//            "message",
//            "data"
//          ],
//          "additionalProperties": false,
//          "properties":
//          {
//            "code":
//            {
//              "type": "integer",
//              "enum":
//              [
//                3158
//              ]
//            },
//            "message":
//            {
//              "type": "string"
//            },
//            "data":
//            {
//              "type": "object",
//              "required":
//              [
//                "providedWithdrawal",
//                "computedWithdrawal"
//              ],
//              "additionalProperties": false,
//              "properties":
//              {
//                "providedWithdrawal":
//                {
//                  "$ref": "cardano.json#/definitions/Value<AdaOnly>"
//                },
//                "expectedWithdrawal":
//                {
//                  "$ref": "cardano.json#/definitions/Value<AdaOnly>"
//                }
//              }
//            }
//          }
//        }
