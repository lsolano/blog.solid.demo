package com.malpeza.solid.isp.model

class PlasticInfo(val secret: String, val pin: String) {
  
}

object PlasticInfo {
  def apply(secret: String, pin: String) = 
    new PlasticInfo(secret, pin)
}