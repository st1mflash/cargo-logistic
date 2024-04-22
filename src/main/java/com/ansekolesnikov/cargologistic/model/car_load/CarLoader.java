package com.ansekolesnikov.cargologistic.model.car_load;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import com.ansekolesnikov.cargologistic.model.car_load.algorithm.AlgorithmLoadHalf;
import com.ansekolesnikov.cargologistic.model.car_load.algorithm.AlgorithmLoadMax;
import com.ansekolesnikov.cargologistic.model.car_load.algorithm.AlgorithmLoadType;

public class CarLoader {
    public void load(String algorithm, CargoCar cargoCar, CargoPackage cargoPackage) {
        switch (algorithm) {
            case "max":
                new AlgorithmLoadMax().load(cargoCar, cargoPackage);
                break;
            case "half":
                new AlgorithmLoadHalf().load(cargoCar, cargoPackage);
                break;
            case "type":
                new AlgorithmLoadType().load(cargoCar, cargoPackage);
                break;
            default:
                break;
        }
    }
}
