package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.model.car.CarInfo;
import com.ansekolesnikov.cargologistic.model.file.LocalFileImportUtils;
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
            return getCargoInfoFromFile(localFile);
        } else {
            LOGGER.error(fileValidation.getLogErrorMessage());
            return fileValidation.getUserErrorMessage();
        }
    }

    private String getCargoInfoFromFile(LocalFile localFile) {
        List<Car> carList = new LocalFileImportUtils().importCarsFromFile(localFile);
        if (carList != null) {
            StringBuilder result = new StringBuilder();
            for (Car car : carList) {
                result.append(new CarInfo().getCarFullInfo(car));
            }
            return result.toString();
        } else {
            LOGGER.info("Указанный файл '" + localFile.getPathNameFormat() + "' не содержит информации о грузовиках");
            return "Указанный файл не содержит информации о грузовиках.";
        }
    }
}