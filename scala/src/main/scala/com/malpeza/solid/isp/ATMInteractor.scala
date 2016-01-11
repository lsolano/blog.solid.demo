package com.malpeza.solid.isp

class ATMInteractor {
  def validateCustomer(request: CustomerValidationRequest) = {
    CustomerValidation()
  }
}

object ATMInteractor {
  def apply(securityService: AnyRef, withdrawService: AnyRef,
    depositService: AnyRef, currencyRatessService: AnyRef): ATMInteractor = {
    require(securityService != null)
    new ATMInteractor()
  }
}