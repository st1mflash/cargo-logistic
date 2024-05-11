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
public class DeleteCarDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public DeleteCarDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public void delete(Car car) {
        try {
            statement.executeUpdate(
                    "DELETE FROM car_model WHERE id = '" + car.getId() + "';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
