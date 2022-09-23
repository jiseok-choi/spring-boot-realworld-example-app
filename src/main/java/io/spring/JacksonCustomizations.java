package io.spring;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonCustomizations {

  @Bean
  public Module realWorldModules() {
    return new RealWorldModules();
  }

  public static class RealWorldModules extends SimpleModule {
    public RealWorldModules() {
      addSerializer(LocalDateTime.class, new DateTimeSerializer());
    }
  }

  public static class DateTimeSerializer extends StdSerializer<LocalDateTime> {

    protected DateTimeSerializer() {
      super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
      if (value == null) {
        gen.writeNull();
      } else {
        gen.writeString(DateTimeFormatter.ISO_DATE_TIME.format(value));
      }
    }
  }
}
