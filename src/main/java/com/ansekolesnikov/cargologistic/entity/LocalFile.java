package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LocalFile {
    private String name, format, path, content;
    private static final Logger LOGGER = Logger.getLogger(LocalFile.class.getName());

    public LocalFile(String filePath) {
        try {
            name = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.lastIndexOf('.'));
            format = filePath.substring(filePath.lastIndexOf('.'));
            path = filePath.substring(0, filePath.lastIndexOf('/') + 1);
            content = Files.readString(Paths.get(path + name + format));
        } catch (Exception e) {
            LOGGER.error("Ошибка считывания содержимого файла '" + filePath + "'. Подробнее: " + e);
        }
    }

    public String calculateFilePathNameFormat() {
        return path + name + format;
    }

    public List<JSONObject> importListJsonCars() {
        List<JSONObject> listJSONObj = new ArrayList<>();
        String[] arrClearContent = content
                .replaceAll("[{\\[\\]]", "")
                .split("},");
        for (String s : arrClearContent) {
            listJSONObj.add(new JSONObject("{" + s + "}"));
        }
        return listJSONObj;
    }

    public List<Car> importCarsFromFile() {
        try {
            return importListJsonCars()
                    .stream()
                    .map(Car::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Ошибка импорта грузовиков из файла: '" + calculateFilePathNameFormat() + "': " + e);
            return null;
        }
    }

    public List<Pack> importPacksFromFile(PackModelDao packModelDao) {
        try {
            return Arrays
                    .stream(content.split("\\n\\s*\\n"))
                    .map(c -> c.replaceAll(" ", "").charAt(0))
                    .map(packModelDao::findByCode)
                    .map(PackModelEntity::to)
                    .map(Pack::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Ошибка ошибка импорта грузов из файла: '" + calculateFilePathNameFormat() + "': " + e);
            return null;
        }
    }
}
