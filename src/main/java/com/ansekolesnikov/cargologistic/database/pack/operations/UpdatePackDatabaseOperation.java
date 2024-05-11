package com.ansekolesnikov.cargologistic.database.pack.operations;

import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Getter
public class UpdatePackDatabaseOperation {
    private DatabaseService databaseService;
    private Statement statement;

    public UpdatePackDatabaseOperation(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public void update(Pack pack) {
        try {
            statement.executeUpdate(
                    "UPDATE pack_model SET " +
                            "name = '" + pack.getName() + "', " +
                            "code = '" + pack.getCode() + "', " +
                            "scheme = '" + pack.getScheme() + "', " +
                            "scheme_width = " + pack.getWidth() + ", " +
                            "scheme_height = " + pack.getHeight() + " " +
                            "WHERE id = " + pack.getId() + ";"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
