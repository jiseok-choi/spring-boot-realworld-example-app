package io.spring.application;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeCursor extends PageCursor<LocalDateTime> {

  public DateTimeCursor(LocalDateTime data) {
    super(data);
  }

  @Override
  public String toString() {
    return String.valueOf(getData().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
  }

  public static DateTime parse(String cursor) {
    if (cursor == null) {
      return null;
    }
    return new DateTime().withMillis(Long.parseLong(cursor)).withZone(DateTimeZone.UTC);
  }
}
