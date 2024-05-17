package com.ansekolesnikov.cargologistic.model.load.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadAlgorithmMaxTest {
    /*
    @Test
    public void testLoad() {
        Car car = new Car();
        car.initCargoFromString("555551000000000000000000000000000000");
        CarUtils carUtils = new CarUtils();
        PackModel packModel = new PackModel(3);
        int carPercentLoadBeforeTryLoad = carUtils.calcPercentLoad(car);
        LoadAlgorithmMax loadAlgorithmMax = new LoadAlgorithmMax();
        loadAlgorithmMax.load(car, packModel);

        assertTrue(carPercentLoadBeforeTryLoad < carUtils.calcPercentLoad(car));
    }
    */

    /*
    @Test
    public void testLoad_fullCar() {
        Car car = new Car();
        car.initCargoFromString("555551999666999666999333111111111111");
        CarUtils carUtils = new CarUtils();
        PackModel packModel = new PackModel(3);
        int carPercentLoadBeforeTryLoad = carUtils.calcPercentLoad(car);
        LoadAlgorithmMax loadAlgorithmMax = new LoadAlgorithmMax();
        loadAlgorithmMax.load(car, packModel);

        assertEquals(carPercentLoadBeforeTryLoad, carUtils.calcPercentLoad(car));
    }
    */
}
