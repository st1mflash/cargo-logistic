package com.ansekolesnikov.cargologistic.model.file;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LocalFileImportUtilsTest {
    @Test
    public void testImportPacksFromFile() {
        LocalFileImportUtils localFileImportUtilsTest = new LocalFileImportUtils();
        LocalFile localFile = new LocalFile("src/test/resources/import/packages/test_packs.txt");

        List<Pack> packs = localFileImportUtilsTest.importPacksFromFile(localFile);

        assertNotNull(packs);
        assertEquals(15, packs.size());
        assertEquals(9, packs.get(0).getType());
        assertEquals(6, packs.get(1).getType());
        assertEquals(5, packs.get(2).getType());
        assertEquals(1, packs.get(3).getType());
        assertEquals(1, packs.get(4).getType());
        assertEquals(3, packs.get(5).getType());
        assertEquals(1, packs.get(6).getType());
        assertEquals(2, packs.get(7).getType());
        assertEquals(3, packs.get(8).getType());
        assertEquals(4, packs.get(9).getType());
        assertEquals(5, packs.get(10).getType());
        assertEquals(6, packs.get(11).getType());
        assertEquals(7, packs.get(12).getType());
        assertEquals(8, packs.get(13).getType());
        assertEquals(9, packs.get(14).getType());
    }

    @Test
    public void testImportCarsFromFile() {
        LocalFileImportUtils localFileImportUtilsTest = new LocalFileImportUtils();
        LocalFile localFile = new LocalFile("src/test/resources/import/car/test_car.json");
        int[][] expectedArrCargo = new int[][]{
                {5, 5, 5, 5, 5, 1},
                {9, 9, 9, 6, 6, 6},
                {9, 9, 9, 6, 6, 6},
                {9, 9, 9, 3, 3, 3},
                {1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };

        List<Car> cars = localFileImportUtilsTest.importCarsFromFile(localFile);

        assertNotNull(cars);
        assertEquals(1, cars.size());
        assertEquals(952472, cars.get(0).getId());
        for (int i = 0; i < Car.HEIGHT; i++) {
            for (int j = 0; j < Car.WIDTH; j++) {
                assertEquals(expectedArrCargo[i][j], cars.get(0).getCargo()[i][j]);
            }
        }
    }
}