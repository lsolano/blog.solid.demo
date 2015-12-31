/*
 * Copyright (c) 2015, 2015, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.srp_dip;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class CleanClientsReportBuilder implements ClientsReportBuilder {

  private final Function<OutputFormat, Optional<Function<Stream<Client>, String>>> formattersFactory;
  private final ClientsRepository clientsRepo;

  public CleanClientsReportBuilder(
      final Function<OutputFormat, Optional<Function<Stream<Client>, String>>> formattersFactory,
      final ClientsRepository clientsRepo) {
    this.formattersFactory = formattersFactory;
    this.clientsRepo = clientsRepo;
  }

  @Override
  public String buildNewClientsReport(final Date from, final Date to, final OutputFormat format) {
    validateReportParameters(from, to, format);

    final Stream<Client> clients = clientsRepo.getBySignOnDate(from, to);

    final Optional<Function<Stream<Client>, String>> formatter = this.formattersFactory.apply(format);

    if (formatter.isPresent()) {
      return formatter.get().apply(clients);
    }

    throw new IllegalArgumentException(String.format("Format not supported '%s'.", format));
  }

  private void validateReportParameters(final Date from, final Date to, final OutputFormat format) {
    if (from == null || to == null || format == null) {
      throw new IllegalArgumentException("Arguments can not be null.");
    }

    if (from.compareTo(to) > 0) {
      throw new IllegalArgumentException(String.format("Invalid date range [%s, %s].", from, to));
    }
  }
}
