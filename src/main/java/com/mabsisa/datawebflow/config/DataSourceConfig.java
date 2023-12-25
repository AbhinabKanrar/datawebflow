/**
 * 
 */
package com.mabsisa.datawebflow.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author abhinab
 *
 */
@Configuration
@EnableAutoConfiguration
public class DataSourceConfig {

  @Bean
  @QuartzDataSource
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource quartzDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @QuartzTransactionManager
  public DataSourceTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}
