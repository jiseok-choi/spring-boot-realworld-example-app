package io.spring.application;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateTimeCursor extends PageCursor<LocalDateTime> {

  public DateTimeCursor(LocalDateTime data) {
    super(data);
  }

  @Override
  public String toString() {
    return String.valueOf(getData().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
  }

  public static LocalDateTime parse(String cursor) {
    if (cursor == null) {
      return null;
    }
    return LocalDateTime.parse(cursor).withNano(Integer.parseInt(cursor)).atZone(ZoneOffset.UTC).toLocalDateTime();
  }
}
