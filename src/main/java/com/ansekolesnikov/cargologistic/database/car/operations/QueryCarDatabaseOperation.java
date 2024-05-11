package com.ansekolesnikov.cargologistic.database.car.operations;

import com.ansekolesnikov.cargologistic.model.car.Car;
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
public class QueryCarDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public QueryCarDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public Car queryById(int id) {
        try {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM car_model WHERE id = " + id
            );
            if (resultSet.next()) {
                return new Car(
                        Integer.parseInt(resultSet.getString(1)),
                        resultSet.getString(2),
                        Integer.parseInt(resultSet.getString(3)),
                        Integer.parseInt(resultSet.getString(4))
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
