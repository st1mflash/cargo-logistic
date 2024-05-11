package com.ansekolesnikov.cargologistic.database;

import com.ansekolesnikov.cargologistic.database.car.CarDatabaseOperations;
import com.ansekolesnikov.cargologistic.database.pack.PackDatabaseOperations;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OperationsDatabase {
    private DatabaseService databaseService;

    private PackDatabaseOperations packOperations;
    private CarDatabaseOperations carOperations;

    public OperationsDatabase(DatabaseService databaseService){
        this.databaseService = databaseService;

        this.packOperations = new PackDatabaseOperations(databaseService);
        this.carOperations = new CarDatabaseOperations();
    }
}
