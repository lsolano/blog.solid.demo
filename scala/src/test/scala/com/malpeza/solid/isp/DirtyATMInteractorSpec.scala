package com.malpeza.solid.isp

import org.scalatest.FlatSpec

import com.malpeza.solid.isp.entities.CurrencyRatesService
import com.malpeza.solid.isp.entities.CustomerRepository
import com.malpeza.solid.isp.entities.DepositService
import com.malpeza.solid.isp.entities.SecurityService
import com.malpeza.solid.isp.entities.WithdrawalService

class DirtyATMInteractorSpec extends FlatSpec {
  behavior of "An ATMInteractor "

  it should "validate Customer if found on repository" in {
    val pin = "1234"

    val repo = CustomerRepository(List(pin -> true))
    val interactor = ATMInteractor(SecurityService(repo), WithdrawalService(), DepositService(), CurrencyRatesService())
    val request = CustomerValidationRequest("DummyPlasticSecret", pin)
    val response = interactor.validate(request)

    assert(response.valid)
  }

  it should "NOT validate Customer if not found on repository" in {
    val pin = "1234"
    val badPin = "1235"

    val repo = CustomerRepository(List(pin -> true))
    val interactor = ATMInteractor(SecurityService(repo), WithdrawalService(), DepositService(), CurrencyRatesService())
    val request = CustomerValidationRequest("DummyPlasticSecret", badPin)
    val response = interactor.validate(request)

    assert(!response.valid)
  }

  it should "allow Withdraw if customer has balance" in {
    val pin = "1234"
    val secServ = SecurityService(CustomerRepository(List(pin -> true)))
    val withdrawSrv = WithdrawalService(Map(pin -> 100.0))
    val interactor = ATMInteractor(secServ, withdrawSrv, DepositService(), CurrencyRatesService())
    val request = WithdrawalRequest(pin, 50.0)
    val response = interactor.withdrawal(request)
    assert(response.done)
  }

  it should "not allow Withdraw if customer has insufficient balance" in {
    val pin = "1234"
    val secServ = SecurityService(CustomerRepository(List(pin -> true)))
    val withdrawSrv = WithdrawalService(Map(pin -> 49.99))
    val interactor = ATMInteractor(secServ, withdrawSrv, DepositService(), CurrencyRatesService())
    val request = WithdrawalRequest(pin, 50.0)
    val response = interactor.withdrawal(request)
    assert(!response.done)
    assert(response.reason.contains(InsufficientBalance))
  }
}