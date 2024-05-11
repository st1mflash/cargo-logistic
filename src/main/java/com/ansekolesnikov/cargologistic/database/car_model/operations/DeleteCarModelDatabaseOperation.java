package com.ansekolesnikov.cargologistic.database.car_model.operations;

import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Getter
public class DeleteCarModelDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public DeleteCarModelDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public void delete(CarModel carModel) {
        try {
            statement.executeUpdate(
                    "DELETE FROM car_model WHERE id = '" + carModel.getIdModel() + "';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
