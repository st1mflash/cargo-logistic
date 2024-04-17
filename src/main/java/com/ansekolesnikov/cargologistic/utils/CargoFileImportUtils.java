package com.ansekolesnikov.cargologistic.utils;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.model.file.CargoTxtFile;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CargoFileImportUtils {
    /*
    public static List<CargoCar> getListCarFromJSONFile (String filePath) throws Exception {
        List<CargoCar> listCargoCars = getListCargoFromJSONFile(filePath);
        //for (CargoCar cargoCar : listCargoCars) {
            //cargo.printCargoFullInfo();
        //}
        return null;
    };
    */
    private static final Logger LOGGER = Logger.getLogger(CargoFileImportUtils.class);
    public static List<CargoCar> getListCargoFromJSONFile(String filePath) throws Exception {
        List<CargoCar> listCargoCars = new ArrayList<>();

        Scanner scanner = new Scanner(new FileReader(filePath));
        if (scanner.hasNext()) {
            String content = scanner.next().replaceAll("[{\\[\\]]", "");
            String[] arrJsonObj = content.split("},");
            try {
                for (String s : arrJsonObj) {
                    listCargoCars.add(CargoCar.exportCargoFromJSON(new JSONObject("{" + s + "}")));
                }
            } catch (Exception e) {
                LOGGER.error("Ошибка в файле '" + filePath + "', не удалось получить содержимое в JSON формате");
                throw new Exception("Ошибка в содержимом файла '" + filePath + "', пожалуйста, проверьте корректность формата JSON, либо используйте другой файл");
            }
        }
        return listCargoCars;
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
