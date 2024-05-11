package com.ansekolesnikov.cargologistic.database.pack;

import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;

public class PackOperationsDatabase {
    private InsertPackDatabase insertPackDatabase;
    private QueryPackDatabase queryPackDatabase;
    private DeletePackDatabase deletePackDatabase;
    public PackOperationsDatabase(DatabaseService databaseService){
        this.insertPackDatabase = new InsertPackDatabase(databaseService);
        this.queryPackDatabase = new QueryPackDatabase(databaseService);
        this.deletePackDatabase = new DeletePackDatabase();
    }
    public void insert(Pack pack){
        insertPackDatabase.insert(pack);
    }
}
