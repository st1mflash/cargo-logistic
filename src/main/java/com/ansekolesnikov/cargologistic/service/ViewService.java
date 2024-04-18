package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.utils.CargoFileImportUtils;
import com.ansekolesnikov.cargologistic.validation.ViewServiceValidation;
import org.apache.log4j.Logger;

import java.util.List;

public class ViewService {
    private static final Logger LOGGER = Logger.getLogger(ViewService.class.getName());
    private static final String PATH_IMPORT_JSON = "src/main/resources/import/cargo/";

    public static String checkService(String fileName) {
        String filePath = PATH_IMPORT_JSON + fileName;
        ViewServiceValidation viewServiceValidation = new ViewServiceValidation();
        if (viewServiceValidation.isFileExist(filePath)) {
            return getCargoFullInfoFromJSONFile(filePath);
        } else {
            LOGGER.error(viewServiceValidation.getMessage());
            return "Указанный файл не найден.";
        }
    }

    private static String getCargoFullInfoFromJSONFile(String filePath) {
        List<CargoCar> cargoCarList = CargoFileImportUtils.getListCargoFromJSONFile(filePath);
        if (cargoCarList != null) {
            StringBuilder result = new StringBuilder();
            for (CargoCar cargoCar : cargoCarList) {
                result.append(cargoCar.getCargoCarFullInfo());
            }
            return result.toString();
        } else {
            LOGGER.info("Указанный файл '" + filePath + "' не содержит информации о грузовиках");
            return "Указанный файл не содержит информации о грузовиках.";
        }
    }
}
