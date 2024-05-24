package com.ansekolesnikov.cargologistic.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Component
@Getter
@Setter
public class DatabaseConnect {
    private String connection_url;
    private String connection_username;
    private String connection_password;

    private Connection connection;
    public Statement statement;
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnect.class.getName());

    public DatabaseConnect(
            String URL,
            String username,
            String password
    ) {
        this.connection_url = URL;
        this.connection_username = username;
        this.connection_password = password;

        try {
            connection = DriverManager.getConnection(connection_url, connection_username, connection_password);
            statement = connection.createStatement();

        } catch (SQLException e) {
            LOGGER.error("Ошибка подключения к базе данных " + e);
            throw new RuntimeException(e);
        }
    }
}
