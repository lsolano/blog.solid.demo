/*
 * Copyright (c) 2015, 2015, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.srp_dip;

import java.util.Date;

import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import com.malpeza.solid.srp_dip.CleanClientsReportBuilder;
import com.malpeza.solid.srp_dip.ClientsReportBuilder;
import com.malpeza.solid.srp_dip.ClientsReportFormatter;
import com.malpeza.solid.srp_dip.InMemoryClientsRepository;
import com.malpeza.solid.srp_dip.OutputFormat;

@RunWith(Theories.class)
public class SRAndDIPrinciplesNegativeTest {

  @DataPoints
  public static final OutputFormat[] INVALID_Formats = { null, OutputFormat.None };

  @DataPoints
  public static final Date[] INVALID_Dates = { null };

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  @Rule
  public final Timeout globalTimeout = Timeout.millis(100);

  @Theory
  public void test_invalid_format(final OutputFormat format) {
    thrown.expect(IllegalArgumentException.class);

    final ClientsReportBuilder clientReportBuilder = getReporter();
    final DateRange dateRange = DateRange.buildFromNowWithMinutesDifference(24 * 60);

    clientReportBuilder.buildNewClientsReport(dateRange.from, dateRange.to, format);
  }

  @Theory
  public void test_invalid_dates(final Date from, final Date to) {
    thrown.expect(IllegalArgumentException.class);

    final ClientsReportBuilder clientReportBuilder = getReporter();

    clientReportBuilder.buildNewClientsReport(from, to, OutputFormat.XML);
  }

  @Theory
  public void test_inverted_dates() {
    thrown.expect(IllegalArgumentException.class);

    final ClientsReportBuilder clientReportBuilder = getReporter();

    final DateRange dateRange = DateRange.buildFromNowWithMinutesDifference(24 * 60);

    clientReportBuilder.buildNewClientsReport(dateRange.to, dateRange.from, OutputFormat.XML);
  }

  private ClientsReportBuilder getReporter() {
    final ClientsReportBuilder clientReportBuilder = new CleanClientsReportBuilder(
        ClientsReportFormatter.buildFormattersFactory(), new InMemoryClientsRepository());

    return clientReportBuilder;
  }
}
