package com.ansekolesnikov.cargologistic.database.pack;

import com.ansekolesnikov.cargologistic.database.pack.operations.DeletePackDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.pack.operations.InsertPackDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.pack.operations.QueryPackDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.pack.operations.UpdatePackDatabaseOperation;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;

public class PackDatabaseOperations {
    private QueryPackDatabaseOperation queryPackDatabaseOperation;
    private InsertPackDatabaseOperation insertPackDatabaseOperation;
    private UpdatePackDatabaseOperation updatePackDatabaseOperation;
    private DeletePackDatabaseOperation deletePackDatabaseOperation;

    public PackDatabaseOperations(DatabaseService databaseService) {
        this.queryPackDatabaseOperation = new QueryPackDatabaseOperation(databaseService);
        this.insertPackDatabaseOperation = new InsertPackDatabaseOperation(databaseService);
        this.updatePackDatabaseOperation = new UpdatePackDatabaseOperation(databaseService);
        this.deletePackDatabaseOperation = new DeletePackDatabaseOperation(databaseService);
    }

    public Pack queryById(int id) {
        return queryPackDatabaseOperation.queryById(id);
    }

    public void insert(Pack pack) {
        insertPackDatabaseOperation.insert(pack);
    }

    public void update(Pack pack) {
        updatePackDatabaseOperation.update(pack);
    }

    public void delete(Pack pack) {
        deletePackDatabaseOperation.delete(pack);
    }
}
