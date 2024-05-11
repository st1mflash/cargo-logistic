package com.ansekolesnikov.cargologistic.database.car_model.operations;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Getter
public class QueryCarModelDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public QueryCarModelDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public CarModel queryById(int id) {
        try {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM car_model WHERE id = " + id
            );
            if (resultSet.next()) {
                return new CarModel(
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
