package com.malpeza.solid.isp.model

class Withdrawal(val pin: String, val amount: Double) {

}

object Withdrawal {
  def apply(pin: String, amount: Double) = new Withdrawal(pin, amount)
}