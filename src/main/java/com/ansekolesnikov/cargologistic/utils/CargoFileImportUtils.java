package com.ansekolesnikov.cargologistic.utils;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.CargoFile;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CargoFileImportUtils {
    private static final Logger LOGGER = Logger.getLogger(CargoFileImportUtils.class);

    public static List<CargoCar> getListCargoFromJSONFile(String filePath) {
        try {
            List<CargoCar> listCargoCars = new ArrayList<>();
            List<JSONObject> listJSONObj = parseJSON(new CargoFile(filePath).getContent());

            for (JSONObject JSONObj : listJSONObj) {
                listCargoCars.add(CargoCar.exportCargoFromJSON(JSONObj));
            }

            return listCargoCars;
        } catch (Exception e) {
            LOGGER.error("Ошибка считывания JSON файла: '" + filePath + "'");
        }
        return null;
    }

    private static List<JSONObject> parseJSON(String content) {
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
