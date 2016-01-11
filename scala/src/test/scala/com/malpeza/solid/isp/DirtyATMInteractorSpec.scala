package com.malpeza.solid.isp

import org.scalatest.FlatSpec

class DirtyATMInteractorSpec extends FlatSpec {
  "An ATMInteractor " should " not validate a Customer without SecurityService" in {
    intercept[IllegalArgumentException] {
      val interactor = ATMInteractor(null, null, null, null)
      val request = CustomerValidationRequest()
      interactor.validateCustomer(request)
    }
  }
}