package com.ansekolesnikov.cargologistic.model;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CargoFileTest {
    @Test
    void testGetPath(){
        CargoFile cargoFile = new CargoFile("src/test/resources/testfile.txt");
        String expectedPath = "src/test/resources/";
        assertEquals(expectedPath, cargoFile.getPath());
    }

    @Test
    void testGetFormat() throws IOException {
        CargoFile cargoFile = new CargoFile("src/test/resources/testfile.txt");
        String expectedFormat = ".txt";
        assertEquals(expectedFormat, cargoFile.getFormat());
    }

    @Test
    void testGetName() throws IOException {
        CargoFile cargoFile = new CargoFile("src/test/resources/testfile.txt");
        String expectedName = "testfile";
        assertEquals(expectedName, cargoFile.getName());
    }

    @Test
    void testGetPathNameFormat() throws IOException {
        CargoFile cargoFile = new CargoFile("src/test/resources/testfile.txt");
        String expectedPathNameFormat = "src/test/resources/testfile.txt";
        assertEquals(expectedPathNameFormat, cargoFile.getPathNameFormat());
    }

}