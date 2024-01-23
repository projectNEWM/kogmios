package io.newm.kogmios.protocols.model.fault

import io.newm.kogmios.protocols.model.Ada
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The transaction is trying to withdraw more funds than specified in a governance action! The field 'data.providedWithdrawal' indicates the amount specified in the transaction, whereas 'data.computedWithdrawal' is the actual amount as computed by the ledger.
 */
@Serializable
@SerialName("3158")
data class TreasuryWithdrawalMismatchFault(
    @SerialName("code")
    override val code: Long,
    @SerialName("message")
    override val message: String,
    @SerialName("data")
    override val data: TreasuryWithdrawalMismatchFaultData,
) : Fault

@Serializable
data class TreasuryWithdrawalMismatchFaultData(
    @SerialName("providedWithdrawal")
    val providedWithdrawal: Ada,
    @SerialName("computedWithdrawal")
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
