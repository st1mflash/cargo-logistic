package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.CargoFile;
import com.ansekolesnikov.cargologistic.model.car.CarInfo;
import com.ansekolesnikov.cargologistic.utils.CargoFileImportUtils;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewCarService implements CargoService {
    private static final Logger LOGGER = Logger.getLogger(ViewCarService.class.getName());
    private static final String PATH_IMPORT = "src/main/resources/import/cargo/";
    private CargoFile cargoFile;

    public ViewCarService() {
        LOGGER.info("Сервис получения полной информации о грузовиках - успешно запущен!");
    }

    @Override
    public String runService(String fileName) {
        this.cargoFile = new CargoFile(PATH_IMPORT + fileName);
        FileValidation fileValidation = new FileValidation(cargoFile);
        if (fileValidation.isValid()) {
            return getCargoInfoFromFile();
        } else {
            LOGGER.error(fileValidation.getLogErrorMessage());
            return fileValidation.getUserErrorMessage();
        }
    }

    private String getCargoInfoFromFile() {
        List<Car> carList = new CargoFileImportUtils().importCarsFromFile(cargoFile);
        if (carList != null) {
            StringBuilder result = new StringBuilder();
            for (Car car : carList) {
                result.append(new CarInfo().getCarFullInfo(car));
            }
            return result.toString();
        } else {
            LOGGER.info("Указанный файл '" + cargoFile.getPathNameFormat() + "' не содержит информации о грузовиках");
            return "Указанный файл не содержит информации о грузовиках.";
        }
    }
}