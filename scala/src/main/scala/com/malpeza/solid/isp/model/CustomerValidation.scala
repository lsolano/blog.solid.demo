package com.malpeza.solid.isp.model

class CustomerValidation(val valid: Boolean) {
}

object CustomerValidation {
  def apply(valid: Boolean) = new CustomerValidation(valid)
}