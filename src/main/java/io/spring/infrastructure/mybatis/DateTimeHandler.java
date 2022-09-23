package io.spring.infrastructure.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedTypes(LocalDateTime.class)
public class DateTimeHandler implements TypeHandler<LocalDateTime> {

  private static final Calendar UTC_CALENDAR = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

  @Override
  public void setParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setTimestamp(
        i, parameter != null ? new Timestamp(parameter.getNano()) : null, UTC_CALENDAR);
  }

  @Override
  public LocalDateTime getResult(ResultSet rs, String columnName) throws SQLException {
    Timestamp timestamp = rs.getTimestamp(columnName, UTC_CALENDAR);
    return timestamp != null ? LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), TimeZone.getDefault().toZoneId()) : null;
  }

  @Override
  public LocalDateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
    Timestamp timestamp = rs.getTimestamp(columnIndex, UTC_CALENDAR);
    return timestamp != null ? LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), TimeZone.getDefault().toZoneId()) : null;
  }

  @Override
  public LocalDateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
    Timestamp ts = cs.getTimestamp(columnIndex, UTC_CALENDAR);
    return ts != null ? LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), TimeZone.getDefault().toZoneId()) : null;
  }
}
