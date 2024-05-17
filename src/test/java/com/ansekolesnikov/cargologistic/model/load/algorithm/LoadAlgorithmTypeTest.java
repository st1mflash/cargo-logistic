package com.ansekolesnikov.cargologistic.model.load.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadAlgorithmTypeTest {
    /*
    @Test
    public void testLoad_emptyCar() {
        Car car = new Car();
        CarUtils carUtils = new CarUtils();
        PackModel packModel = new PackModel(3);
        int carPercentLoadBeforeTryLoad = carUtils.calcPercentLoad(car);
        LoadAlgorithmType loadAlgorithmType = new LoadAlgorithmType();
        loadAlgorithmType.load(car, packModel);

        assertTrue(carPercentLoadBeforeTryLoad < carUtils.calcPercentLoad(car));
    }
    */

    /*
    @Test
    public void testLoad_successfully() {
        Car car = new Car();
        car.initCargoFromString("333000000000000000000000000000000000");
        CarUtils carUtils = new CarUtils();
        PackModel packModel = new PackModel(3);
        int carPercentLoadBeforeTryLoad = carUtils.calcPercentLoad(car);
        LoadAlgorithmType loadAlgorithmType = new LoadAlgorithmType();
        loadAlgorithmType.load(car, packModel);

        assertTrue(carPercentLoadBeforeTryLoad < carUtils.calcPercentLoad(car));
    }
    */

    /*
    @Test
    public void testLoad_notSuccessfully() {
        Car car = new Car();
        car.initCargoFromString("333000000000000000000000000000000000");
        CarUtils carUtils = new CarUtils();
        PackModel packModel = new PackModel(4);
        int carPercentLoadBeforeTryLoad = carUtils.calcPercentLoad(car);
        LoadAlgorithmType loadAlgorithmType = new LoadAlgorithmType();
        loadAlgorithmType.load(car, packModel);

        assertEquals(carPercentLoadBeforeTryLoad, carUtils.calcPercentLoad(car));
    }
    */
}
