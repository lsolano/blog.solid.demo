package com.malpeza.solid.isp

class CustomerValidationRequest(val secret: String, val pin: String) {
}

object CustomerValidationRequest {
  def apply(secret: String, pin: String) =
    new CustomerValidationRequest(secret, pin)
}