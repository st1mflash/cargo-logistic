package com.ansekolesnikov.cargologistic.model.load.algorithm;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadAlgorithmHalfTest {
    @Test
    public void testLoad_underFiftyPercent() {
        Car car = new Car();
        car.initCargoFromString("555551000000000000000000000000000000");
        CarUtils carUtils = new CarUtils();
        PackModel packModel = new PackModel(3);
        int carPercentLoadBeforeTryLoad = carUtils.calcPercentLoad(car);
        LoadAlgorithmHalf loadAlgorithmHalf = new LoadAlgorithmHalf();
        loadAlgorithmHalf.load(car, packModel);

        assertTrue(carPercentLoadBeforeTryLoad < carUtils.calcPercentLoad(car));
    }

    @Test
    public void testLoad_overFiftyPercent() {
        Car car = new Car();
        car.initCargoFromString("555551999666999666999333100000000000");
        CarUtils carUtils = new CarUtils();
        PackModel packModel = new PackModel(3);
        int carPercentLoadBeforeTryLoad = carUtils.calcPercentLoad(car);
        LoadAlgorithmHalf loadAlgorithmHalf = new LoadAlgorithmHalf();
        loadAlgorithmHalf.load(car, packModel);

        assertEquals(carPercentLoadBeforeTryLoad, carUtils.calcPercentLoad(car));
    }
}
