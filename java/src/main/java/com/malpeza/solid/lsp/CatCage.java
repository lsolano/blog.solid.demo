/*
 * Copyright (c) 2015, 2016, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.lsp;

public class CatCage extends PetCage {
  
  @Override
  public boolean allows(Animal pet) {
    return pet instanceof Cat;
  }
  
  @Override
  public void enter(Animal pet) {
    /* If we change pet's type to Cat won't compile: not all Animals are cats */
    if (pet instanceof Cat) {
      super.enter(pet);
      return;
    }
    
    throw new IllegalArgumentException("Only cats are accepted.");
  }
  
  @Override
  public Animal takeOut() {
    /* This is OK because all cats are animals */
    return (Cat)super.takeOut();
  }
}
