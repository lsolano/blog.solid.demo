package com.malpeza.solid.isp.model

class WithdrawalResponse(val done: Boolean, val reason: Option[FailReason]) {
  def this() {
    this(false, Option(CallBank))
  }
}

object WithdrawalResponse {
  def apply() = new WithdrawalResponse()
  def apply(done: Boolean) = new WithdrawalResponse(done, None)
  def apply(failReason: FailReason) = new WithdrawalResponse(false, Option(failReason))
}

sealed trait FailReason

case object CallBank extends FailReason
case object InsufficientBalance extends FailReason