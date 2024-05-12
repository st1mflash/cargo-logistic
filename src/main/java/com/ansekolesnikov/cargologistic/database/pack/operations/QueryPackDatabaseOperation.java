package com.ansekolesnikov.cargologistic.database.pack.operations;

import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Getter
@Component
public class QueryPackDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public QueryPackDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public Pack queryById(int id) {
        try {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM pack_model WHERE id = " + id
            );
            if (resultSet.next()) {
                return new Pack(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3).charAt(0),
                        resultSet.getString(4),
                        Integer.parseInt(resultSet.getString(5)),
                        Integer.parseInt(resultSet.getString(6))
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pack queryByName(String name) {
        try {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM pack_model WHERE name = '" + name + "'"
            );
            if (resultSet.next()) {
                return new Pack(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getString(3).charAt(0),
                        resultSet.getString(4),
                        Integer.parseInt(resultSet.getString(5)),
                        Integer.parseInt(resultSet.getString(6))
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
