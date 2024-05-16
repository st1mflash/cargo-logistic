package com.ansekolesnikov.cargologistic.database.car_model;

import com.ansekolesnikov.cargologistic.database.car_model.operations.DeleteCarModelDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.car_model.operations.InsertCarModelDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.car_model.operations.QueryCarModelDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.car_model.operations.UpdateCarModelDatabaseOperation;
import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
//import org.hibernate.*;

public class CarModelDao {
    private final QueryCarModelDatabaseOperation queryCarModelDatabaseOperation;
    private final InsertCarModelDatabaseOperation insertCarModelDatabaseOperation;
    private final UpdateCarModelDatabaseOperation updateCarModelDatabaseOperation;
    private final DeleteCarModelDatabaseOperation deleteCarModelDatabaseOperation;

    public CarModelDao(DatabaseService databaseService) {
        this.queryCarModelDatabaseOperation = new QueryCarModelDatabaseOperation(databaseService);
        this.insertCarModelDatabaseOperation = new InsertCarModelDatabaseOperation(databaseService);
        this.updateCarModelDatabaseOperation = new UpdateCarModelDatabaseOperation(databaseService);
        this.deleteCarModelDatabaseOperation = new DeleteCarModelDatabaseOperation(databaseService);
    }

    public void save(CarModel carModel) {

    }

    public CarModel queryById(int id) {
        return queryCarModelDatabaseOperation.queryById(id);
    }

    public CarModel queryByName(String name) {
        return queryCarModelDatabaseOperation.queryByName(name);
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
