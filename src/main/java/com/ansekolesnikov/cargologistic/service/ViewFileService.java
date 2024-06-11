package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.validation.ViewFileValidation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewFileService implements IRunnableByStringService {
    private final String PATH_IMPORT_CAR;
    private final PackModelDao packModelDao;

    public ViewFileService(
            PackModelDao packModelDao,
            @Value("${directory.car.import}") String pathImportCar
    ) {
        this.packModelDao = packModelDao;
        this.PATH_IMPORT_CAR = pathImportCar;
    }

    @Override
    public String run(String request) {
        LocalFile localFile = new LocalFile(PATH_IMPORT_CAR + request.split(" ")[1]);
        ViewFileValidation fileValidation = new ViewFileValidation(localFile);

        if (fileValidation.isValid()) {
            return toStringCarsFromFile(localFile);
        } else {
            return fileValidation.getUserErrorMessage();
        }
    }

    public String toStringCarsFromFile(LocalFile localFile) {
        List<Car> importedCarList = localFile.importCarsFromFile();
        if (importedCarList != null) {
            return toStringListCars(importedCarList);
        } else {
            return "Указанный файл не содержит информации о грузовиках.";
        }
    }

    public String toStringListCars(List<Car> carList) {
        StringBuilder result = new StringBuilder();
        for (Car car : carList) {
            result.append(car.toStringCarInfo(packModelDao));
        }
        return result.toString();
    }
}