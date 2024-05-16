package com.ansekolesnikov.cargologistic.database.pack.operations;

import com.ansekolesnikov.cargologistic.model.pack.PackModel;
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

    public void update(PackModel packModel) {
        try {
            statement.executeUpdate(
                    "UPDATE pack_model SET " +
                            "name = '" + packModel.getName() + "', " +
                            "code = '" + packModel.getCode() + "', " +
                            "scheme = '" + packModel.getScheme() + "', " +
                            "scheme_width = " + packModel.getWidth() + ", " +
                            "scheme_height = " + packModel.getHeight() + " " +
                            "WHERE id = " + packModel.getId() + ";"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
