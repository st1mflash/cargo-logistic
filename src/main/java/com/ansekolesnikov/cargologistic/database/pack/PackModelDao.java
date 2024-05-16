package com.ansekolesnikov.cargologistic.database.pack;

import com.ansekolesnikov.cargologistic.database.pack.operations.DeletePackDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.pack.operations.InsertPackDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.pack.operations.QueryPackDatabaseOperation;
import com.ansekolesnikov.cargologistic.database.pack.operations.UpdatePackDatabaseOperation;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;

public class PackModelDao {
    private final QueryPackDatabaseOperation queryPackDatabaseOperation;
    private final InsertPackDatabaseOperation insertPackDatabaseOperation;
    private final UpdatePackDatabaseOperation updatePackDatabaseOperation;
    private final DeletePackDatabaseOperation deletePackDatabaseOperation;

    public PackModelDao(DatabaseService databaseService) {
        this.queryPackDatabaseOperation = new QueryPackDatabaseOperation(databaseService);
        this.insertPackDatabaseOperation = new InsertPackDatabaseOperation(databaseService);
        this.updatePackDatabaseOperation = new UpdatePackDatabaseOperation(databaseService);
        this.deletePackDatabaseOperation = new DeletePackDatabaseOperation(databaseService);
    }

    public PackModel queryById(int id) {
        return queryPackDatabaseOperation.queryById(id);
    }

    public PackModel queryByName(String name) {
        return queryPackDatabaseOperation.queryByName(name);
    }

    public PackModel queryByCode(char code) {
        return queryPackDatabaseOperation.queryByCode(code);
    }

    public void insert(PackModel packModel) {
        insertPackDatabaseOperation.insert(packModel);
    }

    public void update(PackModel packModel) {
        updatePackDatabaseOperation.update(packModel);
    }

    public void delete(PackModel packModel) {
        deletePackDatabaseOperation.delete(packModel);
    }
}
