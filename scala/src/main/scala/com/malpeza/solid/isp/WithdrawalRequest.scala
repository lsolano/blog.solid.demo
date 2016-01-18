package com.malpeza.solid.isp

class WithdrawalRequest(val pin: String, val amount: Double) {

}

object WithdrawalRequest {
  def apply(pin: String, amount: Double) = new WithdrawalRequest(pin, amount)
}