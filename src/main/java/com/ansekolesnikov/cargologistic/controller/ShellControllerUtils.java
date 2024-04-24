package com.ansekolesnikov.cargologistic.controller;

import org.springframework.stereotype.Component;

@Component
public class ShellControllerUtils {
    public String getStringParams(String fileName, String algorithm, String countCars) {
        return fileName + " " + algorithm + " " + countCars;
    }
}
