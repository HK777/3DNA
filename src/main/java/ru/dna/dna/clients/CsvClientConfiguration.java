package ru.dna.dna.clients;

import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvClientConfiguration {

  @Bean
  CsvClient csvClient() {
    return Feign.builder()
        .client(new OkHttpClient())
        .logger(new Slf4jLogger(CsvClient.class))
        .logLevel(Logger.Level.FULL)
        .target(CsvClient.class, "http://web.x3dna.org/");
  }
}
