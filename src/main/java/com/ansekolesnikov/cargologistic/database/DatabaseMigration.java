package com.ansekolesnikov.cargologistic.database;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMigration {
    public DatabaseMigration(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password
    ) {
        Flyway.configure()
                .initSql("SET search_path = public")
                .dataSource(url, username, password)
                .load()
                .migrate();
    }
}
