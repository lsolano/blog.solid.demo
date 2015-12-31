/*
 * Copyright (c) 2015, 2015, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.srp_dip;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public final class ClientsReportFormatter {

  /**
   * Builds a factory function to handle all supported report formats. If the requested format is not managed an empty
   * Option will be returned instead.
   * 
   * @return A factory function consuming an {@link OutputFormat}
   */
  public static final Function<OutputFormat, Optional<Function<Stream<Client>, String>>> buildFormattersFactory() {

    return (final OutputFormat format) -> {
      switch (format) {
      case XML:
        return Optional.of((Stream<Client> clients) -> {
          final StringBuffer sb = new StringBuffer();

          sb.append("<report>");
          clients.parallel()
              .forEach(client -> sb.append("<client><name>").append(client.getName()).append("</name></client>"));
          sb.append("</report>");

          return sb.toString();
        });

      case JSON:
        return Optional.of((Stream<Client> clients) -> {
          final StringBuffer sb = new StringBuffer();

          sb.append('[');
          clients.parallel().forEach(client -> sb.append("{ \"name\": \"").append(client.getName()).append("\" },"));
          sb.deleteCharAt(sb.length() - 1);
          sb.append(']');

          return sb.toString();
        });

      case CSV:
        return Optional.of((Stream<Client> clients) -> {
          final StringBuffer sb = new StringBuffer();

          sb.append(String.format("email,name%n"));
          clients.parallel().forEach(client -> sb.append(client.getEmail()).append(",").append(client.getName()));

          return sb.toString();
        });

      default:
        return Optional.empty();
      }
    };
  }

  private ClientsReportFormatter() {
  }
}
