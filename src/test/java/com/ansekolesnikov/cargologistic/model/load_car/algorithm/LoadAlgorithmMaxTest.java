package com.ansekolesnikov.cargologistic.model.load_car.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarUtils;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadAlgorithmMaxTest {
    @Test
    public void testLoad() {
        Car car = new Car();
        car.initCargoFromString("555551000000000000000000000000000000");
        CarUtils carUtils = new CarUtils();
        Pack pack = new Pack(3);
        int carPercentLoadBeforeTryLoad = carUtils.calcPercentLoad(car);
        LoadAlgorithmHalf loadAlgorithmHalf = new LoadAlgorithmHalf();
        loadAlgorithmHalf.load(car, pack);

        assertTrue(carPercentLoadBeforeTryLoad < carUtils.calcPercentLoad(car));
    }

    @Test
    public void testLoad_fullCar() {
        Car car = new Car();
        car.initCargoFromString("555551999666999666999333111111111111");
        CarUtils carUtils = new CarUtils();
        Pack pack = new Pack(3);
        int carPercentLoadBeforeTryLoad = carUtils.calcPercentLoad(car);
        LoadAlgorithmHalf loadAlgorithmHalf = new LoadAlgorithmHalf();
        loadAlgorithmHalf.load(car, pack);

        assertEquals(carPercentLoadBeforeTryLoad, carUtils.calcPercentLoad(car));
    }
}
