package com.ansekolesnikov.cargologistic.database;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Deprecated
@Component
@Getter
@Setter
public class DatabaseConnect {
    private final String CONNECTION_URL;
    private final String CONNECTION_USERNAME;
    private final String CONNECTION_PASSWORD;

    private final Connection CONNECTION;
    public final Statement STATEMENT;

    private static final Logger LOGGER = Logger.getLogger(DatabaseConnect.class.getName());

    public DatabaseConnect(
            @Value("${spring.datasource.url}") String URL,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password
    ) {
        this.CONNECTION_URL = URL;
        this.CONNECTION_USERNAME = username;
        this.CONNECTION_PASSWORD = password;

        try {
            CONNECTION = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
            STATEMENT = CONNECTION.createStatement();

        } catch (SQLException e) {
            LOGGER.error("Ошибка подключения к базе данных " + e);
            throw new RuntimeException(e);
        }
    }
}
