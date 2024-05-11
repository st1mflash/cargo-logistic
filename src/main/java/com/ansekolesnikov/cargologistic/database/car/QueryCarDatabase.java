package com.ansekolesnikov.cargologistic.database.car;

import com.ansekolesnikov.cargologistic.database.OperationsDatabase;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Statement;

@NoArgsConstructor
@Getter
@Component
public class QueryCarDatabase {
    private DatabaseService databaseService;
    private Statement statement;

    public QueryCarDatabase(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }
}
