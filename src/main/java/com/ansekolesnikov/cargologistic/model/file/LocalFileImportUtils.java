package com.ansekolesnikov.cargologistic.model.file;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.Pack;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LocalFileImportUtils {
    private static final Logger LOGGER = Logger.getLogger(LocalFileImportUtils.class);

    public List<Pack> importPackagesFromFile(LocalFile localFile) {
        try {
            return Arrays.stream(Files.readString(Paths.get(localFile.getPathNameFormat())).split("\\n\\s*\\n"))
                    .map(line -> line.charAt(0) - 48)
                    .map(Pack::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Ошибка ошибка импорта грузов из файла: '" + localFile.getPathNameFormat() + "': " + e);
            return null;
        }
    }

    public List<Car> importCarsFromFile(LocalFile localFile) {
        try {
            List<Car> listCars = new ArrayList<>();
            for (JSONObject JSONObj : parseJSONCar(new LocalFile(localFile.getPathNameFormat()).getContent())) {
                listCars.add(new Car(JSONObj));
            }
            return listCars;
        } catch (Exception e) {
            LOGGER.error("Ошибка импорта грузовиков из файла: '" + localFile.getPathNameFormat() + "': " + e);
            return null;
        }
    }

    private List<JSONObject> parseJSONCar(String content) {
        List<JSONObject> listJSONObj = new ArrayList<>();
        String[] arrClearContent = content
                .replaceAll("[{\\[\\]]", "")
                .split("},");
        for (String s : arrClearContent) {
            listJSONObj.add(new JSONObject("{" + s + "}"));
        }
        return listJSONObj;
    }
}
