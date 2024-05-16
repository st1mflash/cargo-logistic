package com.ansekolesnikov.cargologistic.database;

import com.ansekolesnikov.cargologistic.database.car_model.CarModelDao;
import com.ansekolesnikov.cargologistic.database.pack.PackModelDao;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OperationsDatabase {
    private DatabaseService databaseService;

    private PackModelDao packOperations;
    private CarModelDao carOperations;

    public OperationsDatabase(DatabaseService databaseService){
        this.databaseService = databaseService;

        this.packOperations = new PackModelDao(databaseService);
        this.carOperations = new CarModelDao(databaseService);
    }
}
