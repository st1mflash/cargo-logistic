package com.ansekolesnikov.cargologistic.database.pack.operations;

import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Getter
public class DeletePackDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public DeletePackDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public void delete(PackModel packModel) {
        try {
            statement.executeUpdate(
                    "DELETE FROM pack_model WHERE id = '" + packModel.getId() + "';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
