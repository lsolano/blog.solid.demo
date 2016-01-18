package com.malpeza.solid.isp

import org.scalatest.FlatSpec
import com.malpeza.solid.isp.clean.Withdrawal
import com.malpeza.solid.isp.entities.WithdrawalService

class WithdrawalSpec extends FlatSpec {
  behavior of "Cash withdrawal"

  it should "not work without dependencies" in {
    intercept[IllegalArgumentException] {
      Withdrawal(null)
    }
  }
  
  it should "allow Withdraw if customer has balance" in {
    val pin = "1234"
    val withdrawSrv = WithdrawalService(Map(pin -> 100.0))
    val interactor = Withdrawal(withdrawSrv)
    val request = WithdrawalRequest(pin, 50.0)
    val response = interactor.withdrawal(request)
    assert(response.done)
  }

  it should "not allow Withdraw if customer has insufficient balance" in {
    val pin = "1234"
    val withdrawSrv = WithdrawalService(Map(pin -> 49.99))
    val interactor = Withdrawal(withdrawSrv)
    val request = WithdrawalRequest(pin, 50.0)
    val response = interactor.withdrawal(request)
    assert(!response.done)
    assert(response.reason.contains(InsufficientBalance))
  }
}