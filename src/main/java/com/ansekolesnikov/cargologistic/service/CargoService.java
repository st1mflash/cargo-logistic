package com.ansekolesnikov.cargologistic.service;

public interface CargoService {

    default String runService(String fileName) {
        return null;
    }

    default String runService(String fileName, String algorithm, String countCars) {
        return null;
    }


}
