package com.ansekolesnikov.cargologistic.database.car_model;

import com.ansekolesnikov.cargologistic.database.car_model.operations.DeleteCarModelDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.car_model.operations.InsertCarModelDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.car_model.operations.QueryCarModelDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.car_model.operations.UpdateCarModelDatabaseOperation;
import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;

public class CarModelDatabaseOperations {
    private QueryCarModelDatabaseOperation queryCarModelDatabaseOperation;
    private InsertCarModelDatabaseOperation insertCarModelDatabaseOperation;
    private UpdateCarModelDatabaseOperation updateCarModelDatabaseOperation;
    private DeleteCarModelDatabaseOperation deleteCarModelDatabaseOperation;

    public CarModelDatabaseOperations(DatabaseService databaseService) {
        this.queryCarModelDatabaseOperation = new QueryCarModelDatabaseOperation(databaseService);
        this.insertCarModelDatabaseOperation = new InsertCarModelDatabaseOperation(databaseService);
        this.updateCarModelDatabaseOperation = new UpdateCarModelDatabaseOperation(databaseService);
        this.deleteCarModelDatabaseOperation = new DeleteCarModelDatabaseOperation(databaseService);
    }

    public CarModel queryById(int id) {
        return queryCarModelDatabaseOperation.queryById(id);
    }

    public void insert(CarModel carModel) {
        insertCarModelDatabaseOperation.insert(carModel);
    }

    public void update(CarModel carModel) {
        updateCarModelDatabaseOperation.update(carModel);
    }

    public void delete(CarModel carModel) {
        deleteCarModelDatabaseOperation.delete(carModel);
    }
}
