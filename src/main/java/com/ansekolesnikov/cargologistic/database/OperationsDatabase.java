package com.ansekolesnikov.cargologistic.database;

import com.ansekolesnikov.cargologistic.database.car.CarOperationsDatabase;
import com.ansekolesnikov.cargologistic.database.car.DeleteCarDatabase;
import com.ansekolesnikov.cargologistic.database.car.InsertCarDatabase;
import com.ansekolesnikov.cargologistic.database.car.QueryCarDatabase;
import com.ansekolesnikov.cargologistic.database.pack.DeletePackDatabase;
import com.ansekolesnikov.cargologistic.database.pack.InsertPackDatabase;
import com.ansekolesnikov.cargologistic.database.pack.PackOperationsDatabase;
import com.ansekolesnikov.cargologistic.database.pack.QueryPackDatabase;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Statement;

@NoArgsConstructor
@Getter
@Setter
public class OperationsDatabase {
    private DatabaseService databaseService;

    private PackOperationsDatabase packOperations;
    private CarOperationsDatabase carOperations;
    
    public OperationsDatabase(DatabaseService databaseService){
        this.databaseService = databaseService;

        this.packOperations = new PackOperationsDatabase(databaseService);
        this.carOperations = new CarOperationsDatabase();
    }
}
