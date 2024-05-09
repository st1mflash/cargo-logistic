package com.ansekolesnikov.cargologistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class CargoLogisticApplication {

    public static void main(String[] args) {
        SpringApplication.run(CargoLogisticApplication.class, args);
    }

}
