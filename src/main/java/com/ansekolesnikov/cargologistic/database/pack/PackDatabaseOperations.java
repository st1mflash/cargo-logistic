package com.ansekolesnikov.cargologistic.database.pack;

import com.ansekolesnikov.cargologistic.database.pack.operations.DeletePackDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.pack.operations.InsertPackDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.pack.operations.QueryPackDatabaseOperation;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;

public class PackDatabaseOperations {
    private InsertPackDatabaseOperation insertPackDatabaseOperation;
    private QueryPackDatabaseOperation queryPackDatabaseOperation;
    private DeletePackDatabaseOperation deletePackDatabaseOperation;
    public PackDatabaseOperations(DatabaseService databaseService){
        this.insertPackDatabaseOperation = new InsertPackDatabaseOperation(databaseService);
        this.queryPackDatabaseOperation = new QueryPackDatabaseOperation(databaseService);
        this.deletePackDatabaseOperation = new DeletePackDatabaseOperation();
    }
    public void insert(Pack pack){
        insertPackDatabaseOperation.insert(pack);
    }
}
