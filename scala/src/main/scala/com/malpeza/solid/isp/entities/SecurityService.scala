package com.malpeza.solid.isp.entities

import com.malpeza.solid.isp.model.PlasticInfo
import com.malpeza.solid.isp.model.CustomerValidation

class SecurityService(repository: CustomerRepository) {
  def validateCustomer(plasticInfo: PlasticInfo) : CustomerValidation = {
    CustomerValidation(repository contains plasticInfo.pin)
  }
}

object SecurityService {
  def apply(repository: CustomerRepository) =
    new SecurityService(repository)
}
