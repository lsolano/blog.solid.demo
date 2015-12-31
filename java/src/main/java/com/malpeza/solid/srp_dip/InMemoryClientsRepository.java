/*
 * Copyright (c) 2015, 2015, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.srp_dip;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class InMemoryClientsRepository implements ClientsRepository {

  private final Map<String, Client> db;

  public InMemoryClientsRepository() {
    db = new HashMap<>();
  }

  public InMemoryClientsRepository(final Collection<Client> clients) {
    db = new HashMap<>();
    clients.stream().forEach(client -> db.put(client.getEmail(), client));
  }

  @Override
  public Stream<Client> getBySignOnDate(final Date from, final Date to) {
    final Stream<Client> clients = db.values().stream();
    return clients;
  }
}
