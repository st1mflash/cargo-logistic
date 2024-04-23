package com.ansekolesnikov.cargologistic.service.main.view;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.model.file.LocalFileImportUtils;
import com.ansekolesnikov.cargologistic.model.file.LocalFileUtils;
import com.ansekolesnikov.cargologistic.service.main.CargoService;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewCarService implements CargoService {
    private static final Logger LOGGER = Logger.getLogger(ViewCarService.class.getName());
    private static final String PATH_IMPORT = "src/main/resources/import/cargo/";

    public ViewCarService() {

    }

    @Override
    public String runService(String fileName) {
        LocalFile localFile = new LocalFile(PATH_IMPORT + fileName);
        FileValidation fileValidation = new FileValidation(localFile);

        if (fileValidation.isValid()) {
            return getCarsInfoFromFile(localFile);
        } else {
            LOGGER.error(fileValidation.getLogErrorMessage());
            return fileValidation.getUserErrorMessage();
        }
    }

    private String getCarsInfoFromFile(LocalFile localFile) {
        List<Car> importedCarList = new LocalFileImportUtils().importCarsFromFile(localFile);
        if (importedCarList != null) {
            return new ViewCarServiceUtils().getListCarsResultString(importedCarList);
        } else {
            LOGGER.info("Указанный файл '" + new LocalFileUtils().getFullAddress(localFile) + "' не содержит информации о грузовиках");
            return "Указанный файл не содержит информации о грузовиках.";
        }
    }
}