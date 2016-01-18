package com.malpeza.solid.isp.clean

import com.malpeza.solid.isp.entities.SecurityService
import com.malpeza.solid.isp.model.PlasticInfo
import com.malpeza.solid.isp.CustomerValidationRequest
import com.malpeza.solid.isp.CustomerValidationResponse

class CustomerValidation(securityService: SecurityService) {
  require(securityService != null)

  def validate(request: CustomerValidationRequest): CustomerValidationResponse = {
    val validation = securityService.validateCustomer(PlasticInfo(request.secret, request.pin))

    CustomerValidationResponse(validation.valid)
  }
}

object CustomerValidation {
  def apply(securityService: SecurityService) = new CustomerValidation(securityService)
}