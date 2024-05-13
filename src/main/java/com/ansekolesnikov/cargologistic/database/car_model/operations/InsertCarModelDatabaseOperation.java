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
public class InsertCarModelDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public InsertCarModelDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public void insert(CarModel carModel) {
        try {
            ResultSet resultSet = statement.executeQuery(
                    "INSERT INTO car_model (name, cargo_width, cargo_height) " +
                            "VALUES (" +
                            "'" + carModel.getNameModel() + "', " +
                            "'" + carModel.getCargoWidthModel() + "', " +
                            "'" + carModel.getCargoHeightModel() + "') " +
                            "RETURNING id;"
            );
            if (resultSet.next()) {
                carModel.setIdModel(Integer.parseInt(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
