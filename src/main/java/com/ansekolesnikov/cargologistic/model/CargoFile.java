package com.ansekolesnikov.cargologistic.model;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CargoFile {
    private final String name;
    private final String extension;
    private final String path;
    private final String content;
    private static final Logger LOGGER = Logger.getLogger(CargoFile.class.getName());

    public CargoFile(String filePath) throws IOException {
        name = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.lastIndexOf('.'));
        extension = filePath.substring(filePath.lastIndexOf('.'));
        path = filePath.substring(0, filePath.lastIndexOf('/') + 1);
        content = Files.readString(Paths.get(path + name + extension));
    }
    public String getContent() {
        return content;
    }
    /*
    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public String getPath() {
        return path;
    }
     */


}
