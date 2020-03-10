package org.folio.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
  "org.folio.dao",
  "org.folio.service",
  "org.folio.clients",
  "org.folio.rest.impl",
  "org.folio.rest.exceptions"})
public class ApplicationConfig {
}
