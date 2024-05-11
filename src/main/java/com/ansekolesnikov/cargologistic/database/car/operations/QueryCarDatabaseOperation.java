package com.ansekolesnikov.cargologistic.database.car.operations;

import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Statement;

@NoArgsConstructor
@Getter
@Component
public class QueryCarDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public QueryCarDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }
}
