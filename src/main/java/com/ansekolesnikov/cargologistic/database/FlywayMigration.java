package com.ansekolesnikov.cargologistic.database;

import lombok.NoArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class FlywayMigration {
    public FlywayMigration(
            String url,
            String username,
            String password
    ) {

        Flyway flyway = Flyway
                .configure()
                .initSql("SET search_path = public")
                .dataSource(url, username, password)
                .load();
        flyway.migrate();
    }
}
