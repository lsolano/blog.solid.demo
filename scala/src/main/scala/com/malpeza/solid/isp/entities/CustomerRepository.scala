package com.malpeza.solid.isp.entities

class CustomerRepository(data: List[(String, Boolean)]) {
  def contains(pin: String) = data.contains((pin -> true))
}

object CustomerRepository {
  def apply(data: List[(String, Boolean)]) =
    new CustomerRepository(data)
}