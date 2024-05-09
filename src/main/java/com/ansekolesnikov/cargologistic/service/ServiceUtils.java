package com.ansekolesnikov.cargologistic.service;

import org.springframework.stereotype.Component;

@Component
public class ServiceUtils {
    public String getFileNameFromStringParams(String inputParams) {
        return inputParams.split(" ")[0];
    }
    public String getAlgorithmFromStringParams(String inputParams) {
        return inputParams.split(" ")[1].toLowerCase();
    }
    public int getCountCarsFromStringParams(String inputParams) {
        return Integer.parseInt(inputParams.split(" ")[2]);
    }
}
