package com.ansekolesnikov.cargologistic.model.file;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalFileTest {
    @Test
    void testGetPath(){
        LocalFile localFile = new LocalFile("src/test/resources/import/packages/test_packs.txt");
        String expectedPath = "src/test/resources/import/packages/";
        assertEquals(expectedPath, localFile.getPath());
    }

    @Test
    void testGetFormat(){
        LocalFile localFile = new LocalFile("src/test/resources/import/packages/test_packs.txt");
        String expectedFormat = ".txt";
        assertEquals(expectedFormat, localFile.getFormat());
    }

    @Test
    void testGetName(){
        LocalFile localFile = new LocalFile("src/test/resources/import/packages/test_packs.txt");
        String expectedName = "test_packs";
        assertEquals(expectedName, localFile.getName());
    }

    @Test
    void testGetContent(){
        LocalFile localFile = new LocalFile("src/test/resources/import/car/test_car.json");
        String expectedContent = "[{\"width\":\"6\",\"id\":\"952472\",\"cargo\":\"555551999666999666999333100000000000\",\"height\":\"6\"}]";
        assertEquals(expectedContent, localFile.getContent());
    }

}