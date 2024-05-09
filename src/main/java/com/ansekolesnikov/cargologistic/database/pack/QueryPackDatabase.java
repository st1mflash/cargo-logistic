package com.ansekolesnikov.cargologistic.database.pack;

import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Component
public class QueryPackDatabase {
    private DatabaseService databaseService;
    private Statement statement;
    public QueryPackDatabase(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.statement = databaseService.getStatement();
    }

    public String queryCarNameById (int carId) {
        try {
            ResultSet resultSet = statement.executeQuery("select name from car where id = '" + carId + "'");
            if(resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
