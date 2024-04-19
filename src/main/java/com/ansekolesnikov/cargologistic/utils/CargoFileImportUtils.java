package com.ansekolesnikov.cargologistic.utils;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoFile;
import com.ansekolesnikov.cargologistic.model.CargoPackage;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class CargoFileImportUtils {
    private static final Logger LOGGER = Logger.getLogger(CargoFileImportUtils.class);

    public static List<CargoPackage> importPackagesFromFile(CargoFile cargoFile) {
        try {
            return Arrays.stream(Files.readString(Paths.get(cargoFile.getPathNameFormat())).split("\\n\\s*\\n"))
                    .map(line -> line.charAt(0) - 48)
                    .map(CargoPackage::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Ошибка ошибка импорта грузов из файла: '" + cargoFile.getPathNameFormat() + "': " + e);
            return null;
        }
    }

    public static List<CargoCar> importCarsFromFile(CargoFile cargoFile) {
        try {
            List<CargoCar> listCargoCars = new ArrayList<>();
            for (JSONObject JSONObj : parseCargoJSON(new CargoFile(cargoFile.getPathNameFormat()).getContent())) {
                listCargoCars.add(new CargoCar(JSONObj));
            }
            return listCargoCars;
        } catch (Exception e) {
            LOGGER.error("Ошибка импорта грузовиков из файла: '" + cargoFile.getPathNameFormat() + "': " + e);
            return null;
        }
    }

    private static List<JSONObject> parseCargoJSON(String content) {
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
