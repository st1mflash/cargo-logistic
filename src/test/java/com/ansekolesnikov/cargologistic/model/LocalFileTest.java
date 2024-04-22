package com.ansekolesnikov.cargologistic.model;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalFileTest {
    @Test
    void testGetPath(){
        LocalFile localFile = new LocalFile("src/test/resources/testfile.txt");
        String expectedPath = "src/test/resources/";
        assertEquals(expectedPath, localFile.getPath());
    }

    @Test
    void testGetFormat() throws IOException {
        LocalFile localFile = new LocalFile("src/test/resources/testfile.txt");
        String expectedFormat = ".txt";
        assertEquals(expectedFormat, localFile.getFormat());
    }

    @Test
    void testGetName() throws IOException {
        LocalFile localFile = new LocalFile("src/test/resources/testfile.txt");
        String expectedName = "testfile";
        assertEquals(expectedName, localFile.getName());
    }

    @Test
    void testGetPathNameFormat() throws IOException {
        LocalFile localFile = new LocalFile("src/test/resources/testfile.txt");
        String expectedPathNameFormat = "src/test/resources/testfile.txt";
        assertEquals(expectedPathNameFormat, localFile.getPathNameFormat());
    }

}