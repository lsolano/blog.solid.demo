package com.malpeza.solid.isp

class TransactionResponse(val done: Boolean, val reason: Option[FailReason]) {
  def this() {
    this(false, Option(CallBank))
  }
}

object TransactionResponse {
  def apply() = new TransactionResponse()
  def apply(done: Boolean) = new TransactionResponse(done, None)
  def apply(failReason: FailReason) = new TransactionResponse(false, Option(failReason))
  def apply(done: Boolean, reason: Option[FailReason]) = new TransactionResponse(done, reason)
}

sealed trait FailReason

case object CallBank extends FailReason
case object InsufficientBalance extends FailReason