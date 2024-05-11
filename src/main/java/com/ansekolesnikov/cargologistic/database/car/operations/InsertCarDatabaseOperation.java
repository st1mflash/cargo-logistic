package com.ansekolesnikov.cargologistic.database.car.operations;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Getter
public class InsertCarDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public InsertCarDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }


    public void insert(Car car) {
        try {
            ResultSet resultSet = statement.executeQuery(
                    "INSERT INTO car_model (name, cargo_width, cargo_height) " +
                            "VALUES (" +
                            "'" + car.getName() + "', " +
                            "'" + car.getWidth() + "', " +
                            "'" + car.getHeight() + "') " +
                            "RETURNING id;"
            );
            if (resultSet.next()) {
                car.setId(Integer.parseInt(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
