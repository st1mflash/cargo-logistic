package com.ansekolesnikov.cargologistic.database;

import lombok.NoArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class DatabaseMigration {
    public DatabaseMigration(
            String url,
            String username,
            String password
    ) {
        Flyway.configure()
                .initSql("SET search_path = public")
                .dataSource(url, username, password)
                .load()
                .migrate();
    }
}
