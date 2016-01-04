/*
 * Copyright (c) 2015, 2016, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.lsp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NonGenericPetCageTests {

  @Test
  public void allows() {
    Animal a = new Animal();
    Cat c = new Cat();
    Dog d = new Dog();
    PersianCat pc = new PersianCat();

    PetCage cage = new PetCage();
    assertTrue(cage.allows(a));
    assertTrue(cage.allows(c));
    assertTrue(cage.allows(d));
    assertTrue(cage.allows(pc));

    PetCage catCage = new CatCage();
    assertTrue(catCage.allows(c));
    assertTrue(catCage.allows(pc));
    assertFalse(catCage.allows(a));
    assertFalse(catCage.allows(d));
  }

  @Test
  public void empty() {
    PetCage cage = new PetCage();
    PetCage catCage = new CatCage();

    assertTrue(cage.isEmpty());
    assertTrue(catCage.isEmpty());

    Animal a = new Animal();
    Cat c = new Cat();

    cage.enter(a);
    catCage.enter(c);

    assertFalse(cage.isEmpty());
    assertFalse(catCage.isEmpty());

    cage.takeOut();
    catCage.takeOut();

    assertTrue(cage.isEmpty());
    assertTrue(catCage.isEmpty());
  }

  @Test(expected = IllegalStateException.class)
  public void takeOut_from_empty_animal_cage() {
    PetCage cage = new PetCage();

    cage.takeOut();
  }

  @Test(expected = IllegalStateException.class)
  public void takeOut_from_empty_cat_cage() {
    PetCage cage = new CatCage();

    cage.takeOut();
  }

  @Test
  public void enter_cat_sub_type() {
    CatCage catCage = new CatCage();

    PersianCat pc = new PersianCat();
    catCage.enter(pc);

    assertFalse(catCage.isEmpty());
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void invalid_up_cast() {
    CatCage catCage = new CatCage();
    PetCage cage = (PetCage)catCage;
    
    cage.enter(new Dog());
  }
}
