package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
@Service
@Getter
@Setter
public class DatabaseConnectionService {
    @Autowired
    private CarModelDao carModelDao;
    private String connection_url;
    private String connection_username;
    private String connection_password;

    private Connection connection;
    public Statement statement;
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnectionService.class.getName());

    public DatabaseConnectionService(
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
