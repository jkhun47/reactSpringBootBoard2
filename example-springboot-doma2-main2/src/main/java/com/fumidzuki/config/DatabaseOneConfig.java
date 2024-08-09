package com.fumidzuki.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
//import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration("databaseOneConfig")
//@ConfigurationProperties("database.one")
@ConfigurationProperties("spring.datasource.one")
public class DatabaseOneConfig extends HikariConfig implements Config {

  private DataSource dataSource;

  @PostConstruct
  public void postConstruct() {
    this.dataSource = new TransactionAwareDataSourceProxy(new HikariDataSource(this));
  }

  @Override
  public Dialect getDialect() {
    return new MysqlDialect();
  }

  @Override
  public DataSource getDataSource() {
    return this.dataSource;
  }

}
