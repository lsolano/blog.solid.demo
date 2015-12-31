/*
 * Copyright (c) 2015, 2015, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.srp_dip;

import java.util.Date;
import java.util.stream.Stream;

/**
 * 
 * Dummy repository with a single operation: getting clients by sign-on date (range).
 *
 */
public interface ClientsRepository {

  Stream<Client> getBySignOnDate(Date from, Date to);
}
