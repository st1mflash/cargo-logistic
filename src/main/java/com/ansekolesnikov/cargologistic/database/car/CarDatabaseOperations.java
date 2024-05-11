package com.ansekolesnikov.cargologistic.database.car;

import com.ansekolesnikov.cargologistic.database.car.operations.DeleteCarDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.car.operations.InsertCarDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.car.operations.QueryCarDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.car.operations.UpdateCarDatabaseOperation;
import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;

public class CarDatabaseOperations {
    private QueryCarDatabaseOperation queryCarDatabaseOperation;
    private InsertCarDatabaseOperation insertCarDatabaseOperation;
    private UpdateCarDatabaseOperation updateCarDatabaseOperation;
    private DeleteCarDatabaseOperation deleteCarDatabaseOperation;

    public CarDatabaseOperations(DatabaseService databaseService) {
        this.queryCarDatabaseOperation = new QueryCarDatabaseOperation(databaseService);
        this.insertCarDatabaseOperation = new InsertCarDatabaseOperation(databaseService);
        this.updateCarDatabaseOperation = new UpdateCarDatabaseOperation(databaseService);
        this.deleteCarDatabaseOperation = new DeleteCarDatabaseOperation(databaseService);
    }

    public Car queryById(int id) {
        return queryCarDatabaseOperation.queryById(id);
    }

    public void insert(Car car) {
        insertCarDatabaseOperation.insert(car);
    }

    public void update(Car car) {
        updateCarDatabaseOperation.update(car);
    }

    public void delete(Car car) {
        deleteCarDatabaseOperation.delete(car);
    }
}
