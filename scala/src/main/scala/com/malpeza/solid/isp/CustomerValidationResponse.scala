package com.malpeza.solid.isp

class CustomerValidationResponse(val valid: Boolean) {
}

object CustomerValidationResponse {
  def apply(valid: Boolean) = new CustomerValidationResponse(valid)
}