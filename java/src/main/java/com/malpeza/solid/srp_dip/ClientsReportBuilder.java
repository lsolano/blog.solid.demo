/*
 * Copyright (c) 2015, 2015, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.srp_dip;

import java.util.Date;

public interface ClientsReportBuilder {

  /**
   * Builds a report of the clients with sign-on date between the given range, using the output format requested.
   * @param from Date range lower bound
   * @param to Date range upper bound
   * @param format Report's format
   * @return String representation of the report using the given format
   */
  public String buildNewClientsReport(final Date from, final Date to, final OutputFormat format);
}
