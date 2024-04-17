package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.model.CargoCar;
import com.ansekolesnikov.cargologistic.utils.CargoFileImportUtils;
import com.ansekolesnikov.cargologistic.validation.ViewServiceValidation;
import org.apache.log4j.Logger;

import java.util.List;

public class ViewService {
    private static final Logger LOGGER = Logger.getLogger(ViewService.class.getName());
    private static final String PATH_IMPORT_JSON = "src/main/resources/import/cargo/";
    public static String checkService(String fileName) throws Exception {
        String filePath = PATH_IMPORT_JSON + fileName;
        ViewServiceValidation viewServiceValidation = new ViewServiceValidation();
        if (viewServiceValidation.isFileExist(filePath)) {
            return getCargoFullInfoFromJSONFile(filePath);
        } else {
            LOGGER.error(viewServiceValidation.getMessage());
            return viewServiceValidation.getMessage();
        }
    }
    private static String getCargoFullInfoFromJSONFile(String filePath) throws Exception {
        StringBuilder result = new StringBuilder();
        List<CargoCar> cargoCarList = CargoFileImportUtils.getListCargoFromJSONFile(filePath);
        for (CargoCar cargoCar : cargoCarList) {
            result.append(cargoCar.getCargoCarFullInfo());
        }
        return result.toString();
    }
}
