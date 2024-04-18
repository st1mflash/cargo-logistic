package com.ansekolesnikov.cargologistic.utils;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import org.json.JSONObject;

import java.util.List;

public class CargoCarUtils {
    public static void printListCargo(List<CargoCar> listCargoCars) {
        if (listCargoCars != null) {
            for (CargoCar cargoCar : listCargoCars) {
                cargoCar.printCargo();
            }
        }
    }
}
