package com.ansekolesnikov.cargologistic.database.car.operations;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Getter
public class UpdateCarDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public UpdateCarDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public void update(Car car) {
        try {
            statement.executeUpdate(
                    "UPDATE car_model SET " +
                            "name = '" + car.getName() + "', " +
                            "cargo_width = " + car.getWidth() + ", " +
                            "cargo_height = " + car.getHeight() + " " +
                            "WHERE id = " + car.getId() + ";"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
