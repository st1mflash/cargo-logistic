package com.ansekolesnikov.cargologistic.model;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class File {
    public static String convertFileNameToTxtExtension(String fileName) {
        if (!fileName.contains(".txt")) {
            fileName = fileName + ".txt";
        }
        return fileName;
    }

    public static String convertFileNameToJsonExtension(String fileName) {
        if (!fileName.contains(".json")) {
            fileName = fileName + ".json";
        }
        return fileName;
    }

    public static List<Package> getPackagesFromFile(String fileName) throws IOException {
        String filePath = "src/main/resources/import/packages/" + fileName;
        return Arrays.stream(Files.readString(Paths.get(filePath)).split("\\n\\s*\\n"))
                .map(line -> line.charAt(0) - 48)
                .map(Package::new)
                .collect(Collectors.toList());
    }

    public static void exportListCargoToJsonFile(List<Cargo> listCargo) throws IOException {
        StringBuilder jsonList = new StringBuilder();
        String filePathExport = "src/main/resources/export/cargo/loaded_cargo.json";
        for (Cargo cargo : listCargo) {
            jsonList.append(cargo.getJsonString()).append(",");
        }
        if (!jsonList.isEmpty()) {
            jsonList.deleteCharAt(jsonList.length() - 1);
        }

        FileWriter writer = new FileWriter(filePathExport);
        writer.write("[" + jsonList + "]");
        writer.close();

        Logger.getLogger(File.class.getName()).info("Результат погрузки выгружен по пути: '" + filePathExport + "' в формате JSON.");
    }

    public static List<Cargo> importListCargoFromJsonFile(String filePath) throws Exception {
        filePath = "src/main/resources/import/cargo/" + filePath;

        List<Cargo> listCargo = new ArrayList<>();

        Scanner scanner = new Scanner(new FileReader(filePath));
        if (scanner.hasNext()) {
            String content = scanner.next().replaceAll("[{\\[\\]]", "");
            String[] arrJsonObj = content.split("},");
            try {
                for (String s : arrJsonObj) {
                    listCargo.add(Cargo.exportCargoFromJSON(new JSONObject("{" + s + "}")));
                }
            } catch (Exception e) {
                Logger.getLogger(File.class.getName()).error("Ошибка в файле '" + filePath + "', не удалось получить содержимое в JSON формате");
                throw new Exception("Ошибка в содержимом файла '" + filePath + "', пожалуйста, проверьте корректность формата JSON, либо используйте другой файл");
            }
        }
        return listCargo;
    }

}
