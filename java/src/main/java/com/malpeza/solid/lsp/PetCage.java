/*
 * Copyright (c) 2015, 2016, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.lsp;

public class PetCage {
  private Animal prisoner;

  /**
   * Indicates if the cage is full
   * 
   * @return <code>true</code> if the cage has a pet inside.
   */
  public boolean isEmpty() {
    return this.prisoner == null;
  }

  /**
   * Indicates if the given pet can be put inside the cage
   * 
   * @param pet
   * @return <code>true</code> if the pet is allowed to inside this cage
   */
  public boolean allows(Animal pet) {
    return pet != null;
  }

  /**
   * 
   * @param pet
   *          The pet to be put on the cage.
   * @throws IllegalArgumentException
   *           If the argument is not acceptable (too large or small animal?)
   * @throws IllegalStateException
   *           If the cage is not empty.
   */
  public void enter(Animal pet) throws IllegalArgumentException, IllegalStateException {
    if (!isEmpty()) {
      throw new IllegalStateException("The cage must be empty.");
    }

    if (pet == null) {
      throw new IllegalArgumentException("Invalid animal.");
    }

    this.prisoner = pet;
  }

  /**
   * 
   * @return The animal inside the cage
   * @throws IllegalStateException
   *           If the cage is already empty
   */
  public Animal takeOut() throws IllegalStateException {
    if (isEmpty()) {
      throw new IllegalStateException("The cage is already empty.");
    }

    Animal freePet = this.prisoner;
    this.prisoner = null;
    return freePet;
  }
}
