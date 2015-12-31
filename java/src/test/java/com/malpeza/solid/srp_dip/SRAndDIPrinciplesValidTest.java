/*
 * Copyright (c) 2015, 2015, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.srp_dip;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import com.malpeza.solid.srp_dip.CleanClientsReportBuilder;
import com.malpeza.solid.srp_dip.Client;
import com.malpeza.solid.srp_dip.ClientsReportBuilder;
import com.malpeza.solid.srp_dip.ClientsReportFormatter;
import com.malpeza.solid.srp_dip.InMemoryClientsRepository;
import com.malpeza.solid.srp_dip.OutputFormat;

@RunWith(Theories.class)
public class SRAndDIPrinciplesValidTest {
  @Rule
  public final Timeout globalTimeout = Timeout.millis(100);

  @Theory
  public void test_xml_format() {
    final String report = generateReport(OutputFormat.XML);

    assertEquals("<report><client><name>Peter</name></client></report>", report);
  }

  @Theory
  public void test_json_format() {
    final String report = generateReport(OutputFormat.JSON);

    assertEquals("[{ \"name\": \"Peter\" }]", report);
  }

  private String generateReport(final OutputFormat format) {
    final Client[] initialData = { new Client("p.parker@dummy.com", "Peter", "8091234567") };
    final ClientsReportBuilder clientReportBuilder = new CleanClientsReportBuilder(
        ClientsReportFormatter.buildFormattersFactory(), new InMemoryClientsRepository(Arrays.asList(initialData)));

    final DateRange dateRange = DateRange.buildFromNowWithMinutesDifference(24 * 60);

    return clientReportBuilder.buildNewClientsReport(dateRange.from, dateRange.to, format);
  }
}
