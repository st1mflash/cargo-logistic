package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
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
    public String runByStringService(String request) {
        /*
        LocalFile localFile = new LocalFile(PATH_IMPORT_CAR + serviceRequest.getViewFileServiceInput().getFileName());
        FileValidation fileValidation = new FileValidation(localFile);
        ViewFileServiceOutput serviceOutput = new ViewFileServiceOutput();

        if (fileValidation.isValid()) {
            serviceOutput.setText(toStringCarsFromFile(localFile));
        } else {
            serviceOutput.setText(fileValidation.getUserErrorMessage());
        }
        return serviceOutput;

         */
        return "";
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