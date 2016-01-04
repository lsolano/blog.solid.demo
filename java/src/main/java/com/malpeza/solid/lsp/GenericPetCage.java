/*
 * Copyright (c) 2015, 2016, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.lsp;

public class GenericPetCage<T extends Animal> {
  private T prisoner;

  public boolean isEmpty() {
    return prisoner == null;
  }

  public boolean allows(T pet) {
    return pet != null;
  }

  public void enter(T pet) {
    if (!isEmpty()) {
      throw new IllegalStateException("The cage must be empty.");
    }

    if (pet == null) {
      throw new IllegalArgumentException("Invalid animal.");
    }

    this.prisoner = pet;
  }

  public T takeOut() {
    if (isEmpty()) {
      throw new IllegalStateException("The cage is already empty.");
    }
    T freePet = this.prisoner;
    this.prisoner = null;
    return freePet;
  }
}
