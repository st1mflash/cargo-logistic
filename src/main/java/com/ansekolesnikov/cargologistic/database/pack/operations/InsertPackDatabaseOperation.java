package com.ansekolesnikov.cargologistic.database.pack.operations;

import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Getter
public class InsertPackDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public InsertPackDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public void insert(Pack pack) {
        try {
            ResultSet resultSet = statement.executeQuery(
                    "INSERT INTO pack (name, code, scheme, scheme_width, scheme_height) " +
                            "VALUES (" +
                            "'" + pack.getName() + "', " +
                            "'" + pack.getCode() + "'," +
                            "'" + pack.getScheme() + "', " +
                            "'" + pack.getWidth() + "', " +
                            "'" + pack.getHeight() + "') " +
                            "RETURNING id;"
            );
            if(resultSet.next()) {
                pack.setId(Integer.parseInt(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
