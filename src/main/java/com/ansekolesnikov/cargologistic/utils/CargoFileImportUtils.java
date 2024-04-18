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

    /*
    public static void exportListCargoToJsonFile(List<CargoCar> listCargoCars) throws IOException {
        StringBuilder jsonList = new StringBuilder();
        String filePathExport = "src/main/resources/export/cargo/loaded_cargo.json";
        for (CargoCar cargoCar : listCargoCars) {
            jsonList.append(cargoCar.getJsonString()).append(",");
        }
        if (!jsonList.isEmpty()) {
            jsonList.deleteCharAt(jsonList.length() - 1);
        }

        FileWriter writer = new FileWriter(filePathExport);
        writer.write("[" + jsonList + "]");
        writer.close();

        logger.info("Результат погрузки выгружен по пути: '" + filePathExport + "' в формате JSON.");
    }

     */
}
