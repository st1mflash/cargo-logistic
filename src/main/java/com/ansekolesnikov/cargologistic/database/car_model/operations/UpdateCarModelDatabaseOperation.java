package com.ansekolesnikov.cargologistic.database.car_model.operations;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Getter
public class UpdateCarModelDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public UpdateCarModelDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public void update(CarModel carModel) {
        try {
            statement.executeUpdate(
                    "UPDATE car_model SET " +
                            "name = '" + carModel.getNameModel() + "', " +
                            "cargo_width = " + carModel.getCargoWidthModel() + ", " +
                            "cargo_height = " + carModel.getCargoHeightModel() + " " +
                            "WHERE id = " + carModel.getIdModel() + ";"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
