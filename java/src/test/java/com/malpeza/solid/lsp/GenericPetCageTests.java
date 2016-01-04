/*
 * Copyright (c) 2015, 2016, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.lsp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GenericPetCageTests {

  @Test
  public void allows() {
    Animal a = new Animal();
    Cat c = new Cat();
    Dog d = new Dog();

    GenericPetCage<Animal> cage = new GenericPetCage<>();
    assertTrue(cage.allows(a));
    assertTrue(cage.allows(c));
    assertTrue(cage.allows(d));

    GenericPetCage<Cat> catCage = new GenericPetCage<>();
//    GenericPetCage<Cat> catCage2 = new GenericPetCage<Animal>(); /*Does not compile*/
    assertTrue(catCage.allows(c));
    
    /* These lines do not compile */
//    assertFalse(catCage.allows(a));
//    assertFalse(catCage.allows(d));
  }

  @Test
  public void empty() {
    GenericPetCage<Animal> cage = new GenericPetCage<>();
    GenericPetCage<Cat> catCage = new GenericPetCage<>();

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
    GenericPetCage<Animal> cage = new GenericPetCage<>();

    cage.takeOut();
  }

  @Test(expected = IllegalStateException.class)
  public void takeOut_from_empty_cat_cage() {
    GenericPetCage<Cat> catCage = new GenericPetCage<>();

    catCage.takeOut();
  }
  
  @Test
  public void enter_cat_sub_type() {
    GenericPetCage<Cat> catCage = new GenericPetCage<>();

    PersianCat pc = new PersianCat();
    catCage.enter(pc);

    assertFalse(catCage.isEmpty());
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Test
  public void invalid_up_cast() {
    GenericPetCage catCage = new GenericPetCage<Cat>();
    
    GenericPetCage<Animal> cage = (GenericPetCage<Animal>)catCage;
    
    Dog d = new Dog();
    assertTrue(cage.allows(d));
    cage.enter(d);
    
    assertFalse(cage.isEmpty());
  }
}
