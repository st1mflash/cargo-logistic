package com.ansekolesnikov.cargologistic.database.pack;

import com.ansekolesnikov.cargologistic.database.pack.operations.DeletePackDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.pack.operations.InsertPackDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.pack.operations.QueryPackDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.pack.operations.UpdatePackDatabaseOperation;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;

public class PackDatabaseOperations {
    private final QueryPackDatabaseOperation queryPackDatabaseOperation;
    private final InsertPackDatabaseOperation insertPackDatabaseOperation;
    private final UpdatePackDatabaseOperation updatePackDatabaseOperation;
    private final DeletePackDatabaseOperation deletePackDatabaseOperation;

    public PackDatabaseOperations(DatabaseService databaseService) {
        this.queryPackDatabaseOperation = new QueryPackDatabaseOperation(databaseService);
        this.insertPackDatabaseOperation = new InsertPackDatabaseOperation(databaseService);
        this.updatePackDatabaseOperation = new UpdatePackDatabaseOperation(databaseService);
        this.deletePackDatabaseOperation = new DeletePackDatabaseOperation(databaseService);
    }

    public Pack queryById(int id) {
        return queryPackDatabaseOperation.queryById(id);
    }

    public Pack queryByName(String name) {
        return queryPackDatabaseOperation.queryByName(name);
    }

    public Pack queryByCode(char code) {
        return queryPackDatabaseOperation.queryByCode(code);
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
