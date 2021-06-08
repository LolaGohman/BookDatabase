package book.database.application.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories("book.database.application")
public class DatabaseConfig extends AbstractJdbcConfiguration {

    @Bean
    NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    DataSource dataSource(
        @Value("${database.url}") String url,
        @Value("${database.username}") String username,
        @Value("${database.password}") String password,
        @Value("${database.driver}") String driver
    ){
        final BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setDriverClassName(driver);
        return basicDataSource;
    }

    @Bean(initMethod = "migrate", name = "flyway")
    public Flyway flyway(DataSource dataSource) {
        return Flyway
            .configure()
            .dataSource(dataSource)
            .locations("classpath:migrations")
            .outOfOrder(true)
            .table("schema_version")
            .ignoreMissingMigrations(true)
            .ignoreFutureMigrations(true)
            .load();
    }
}
