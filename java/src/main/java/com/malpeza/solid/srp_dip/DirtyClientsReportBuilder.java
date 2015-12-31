/*
 * Copyright (c) 2015, 2015, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.srp_dip;

import java.util.Date;
import java.util.stream.Stream;

public class DirtyClientsReportBuilder implements ClientsReportBuilder {

  @Override
  public String buildNewClientsReport(final Date from, final Date to, final OutputFormat format) {
    if (from == null || to == null || format == null) {
      throw new IllegalArgumentException("Arguments can not be null.");
    }

    if (from.compareTo(to) > 0) {
      throw new IllegalArgumentException(String.format("Invalid date range [%s, %s].", from, to));
    }

    final ClientsRepository clientsRepo = new InMemoryClientsRepository();

    final Stream<Client> clients = clientsRepo.getBySignOnDate(from, to);
    final StringBuffer sb = new StringBuffer();
    switch (format) {
    case XML:
      sb.append("<report>");
      clients.parallel()
          .forEach(client -> sb.append("<client><name>").append(client.getName()).append("</name></client>"));
      sb.append("</report>");
      break;

    case JSON:
      sb.append('[');
      clients.parallel().forEach(client -> sb.append("{ \"name\": \"").append(client.getName()).append("\" },"));
      sb.deleteCharAt(sb.length() - 1);
      sb.append(']');
      break;

    case CSV:
      sb.append(String.format("email,name%n"));
      clients.parallel().forEach(client -> sb.append(client.getEmail()).append(",").append(client.getName()));
      break;

    default:
      throw new IllegalArgumentException(String.format("Format not supported '%s'.", format));
    }

    return sb.toString();
  }
}
