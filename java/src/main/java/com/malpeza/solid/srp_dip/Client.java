/*
 * Copyright (c) 2015, 2015, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.srp_dip;

public class Client {
  private final String email;
  private final String name;
  private final String phone;

  public Client(final String email, final String name, final String phone) {
    this.email = email;
    this.name = name;
    this.phone = phone;
  }

  public final String getEmail() {
    return email;
  }

  public final String getName() {
    return name;
  }

  public final String getPhone() {
    return phone;
  }
}
