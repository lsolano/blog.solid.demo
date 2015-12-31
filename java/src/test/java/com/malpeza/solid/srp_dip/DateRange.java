/*
 * Copyright (c) 2015, 2015, Malpeza and/or its affiliates. 
 * All rights reserved. Use is subject to license terms. 
 */

package com.malpeza.solid.srp_dip;

import java.util.Calendar;
import java.util.Date;

final class DateRange {
  public final Date from;
  public final Date to;

  DateRange(final Date from, final Date to) {
    this.from = from;
    this.to = to;
  }

  static final DateRange buildFromNowWithMinutesDifference(final int deltaMinutes) {
    final Calendar cal = Calendar.getInstance();

    final Date from = cal.getTime();

    cal.add(Calendar.MINUTE, deltaMinutes);

    return new DateRange(from, cal.getTime());
  }
}
