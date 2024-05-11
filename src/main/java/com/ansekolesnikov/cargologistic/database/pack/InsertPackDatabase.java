package com.ansekolesnikov.cargologistic.database.pack;

import com.ansekolesnikov.cargologistic.database.OperationsDatabase;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Getter
//@Component
public class InsertPackDatabase {
    private DatabaseService databaseService;
    private Statement statement;


    public InsertPackDatabase(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public void insert(Pack pack) {
        try {
            statement.executeUpdate(
                    "insert into pack (name, code, scheme, scheme_width, scheme_height) " +
                            "values (" +
                            "'" + pack.getName() + "', " +
                            "'" + pack.getCode() + "'," +
                            "'" + pack.getScheme() + "', " +
                            "'" + pack.getWidth() + "'," +
                            "'" + pack.getHeight() + "');"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
