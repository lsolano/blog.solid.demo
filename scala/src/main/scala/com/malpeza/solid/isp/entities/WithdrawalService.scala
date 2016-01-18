package com.malpeza.solid.isp.entities

import com.malpeza.solid.isp.model.Withdrawal
import com.malpeza.solid.isp.model.WithdrawalResponse
import com.malpeza.solid.isp.model.InsufficientBalance

class WithdrawalService(accounts: Map[String, Double]) {
  val accs = collection.mutable.Map(accounts.toSeq: _*)
  def this() {
    this(Map.empty)
  }

  def withdrawal(withdrawal: Withdrawal): WithdrawalResponse = {
    if (accs.contains(withdrawal.pin)) {
      val acc = accs(withdrawal.pin)
      if (acc >= withdrawal.amount) {
        accs.updated(withdrawal.pin, acc - withdrawal.amount)
        WithdrawalResponse(true)
      } else
        WithdrawalResponse(InsufficientBalance)
    } else {
      WithdrawalResponse(false)
    }
  }
}

object WithdrawalService {
  def apply() = new WithdrawalService()
  def apply(accounts: Map[String, Double]) = new WithdrawalService(accounts)
}