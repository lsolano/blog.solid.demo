package com.malpeza.solid.isp.clean

import com.malpeza.solid.isp.entities.WithdrawalService
import com.malpeza.solid.isp.CallBank
import com.malpeza.solid.isp.InsufficientBalance
import com.malpeza.solid.isp.TransactionResponse
import com.malpeza.solid.isp.WithdrawalRequest

class Withdrawal(withdrawalService: WithdrawalService) {
  def withdrawal(request: WithdrawalRequest): TransactionResponse = {
    val response = withdrawalService.withdrawal(com.malpeza.solid.isp.model.Withdrawal(request.pin, request.amount))
    val failReason = response.reason match {
      case Some(r) => r match {
        case com.malpeza.solid.isp.model.InsufficientBalance => InsufficientBalance
        case _ => CallBank
      }
      case _ => CallBank
    }

    TransactionResponse(response.done, Option(failReason))
  }
}

object Withdrawal {
  def apply(withdrawalService: WithdrawalService) = new Withdrawal(withdrawalService)
}