package com.malpeza.solid.isp

import com.malpeza.solid.isp.model.PlasticInfo
import com.malpeza.solid.isp.entities.SecurityService
import com.malpeza.solid.isp.entities.WithdrawalService

class ATMInteractor(securityService: SecurityService, withdrawalService: WithdrawalService,
    depositService: AnyRef, currencyRatesService: AnyRef) {
  require(securityService != null)
  require(withdrawalService != null)
  require(depositService != null)
  require(currencyRatesService != null)

  def validate(request: CustomerValidationRequest): CustomerValidationResponse = {
    val validation = securityService.validateCustomer(PlasticInfo(request.secret, request.pin))

    CustomerValidationResponse(validation.valid)
  }

  def withdrawal(request: WithdrawalRequest): TransactionResponse = {
    val response = withdrawalService.withdrawal(com.malpeza.solid.isp.model.Withdrawal(request.pin, request.amount))
    val failReason = response.reason match {
      case Some(r) => r match {
        case model.InsufficientBalance => InsufficientBalance
        case _ => CallBank
      }
      case _ => CallBank
    }

    TransactionResponse(response.done, Option(failReason))
  }
  
  def deposit(request: AnyRef) = ???
}

object ATMInteractor {
  def apply(securityService: SecurityService, withdrawalService: WithdrawalService,
    depositService: AnyRef, currencyRatesService: AnyRef): ATMInteractor = {
    new ATMInteractor(securityService, withdrawalService, depositService, currencyRatesService)
  }
}