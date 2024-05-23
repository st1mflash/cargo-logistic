package com.ansekolesnikov.cargologistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CargoLogisticApplication {

    public static void main(String[] args) {
        SpringApplication.run(CargoLogisticApplication.class, args);
    }

}
