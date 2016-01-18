package com.malpeza.solid.isp

import org.scalatest.FlatSpec
import com.malpeza.solid.isp.entities.CustomerRepository
import com.malpeza.solid.isp.entities.SecurityService
import com.malpeza.solid.isp.clean.CustomerValidation

class CustomerValidationSpec extends FlatSpec {
  behavior of "Validating a Customer"

  it should "not work without dependencies" in {
    intercept[IllegalArgumentException] {
      CustomerValidation(null)
    }
  }

  it should "validate Customer if found on repository" in {
    val pin = "1234"

    val repo = CustomerRepository(List(pin -> true))
    val interactor = CustomerValidation(SecurityService(repo))
    val request = CustomerValidationRequest("DummyPlasticSecret", pin)
    val response = interactor.validate(request)

    assert(response.valid)
  }

  it should "NOT validate Customer if not found on repository" in {
    val pin = "1234"
    val badPin = "1235"

    val repo = CustomerRepository(List(pin -> true))
    val interactor = CustomerValidation(SecurityService(repo))
    val request = CustomerValidationRequest("DummyPlasticSecret", badPin)
    val response = interactor.validate(request)

    assert(!response.valid)
  }
}