package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.constants.MessageConstant;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.entity.RequestString;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.mappers.CarModelMapper;
import com.ansekolesnikov.cargologistic.mappers.LocalFileMapper;
import com.ansekolesnikov.cargologistic.repository.PackModelRepository;
import com.ansekolesnikov.cargologistic.validation.ViewFileValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ViewFileService implements IRunnableByStringService {
    @Value("${directory.car.import}")
    private String PATH_IMPORT_CAR;
    private final PackModelRepository packModelRepository;
    private final CarModelMapper carModelMapper;
    private final LocalFileMapper localFileMapper;
    private final CarService carService;
    private final LocalFileService localFileService;

    @Override
    public String run(RequestString request) {
        LocalFile localFile = localFileMapper.toLocalFile(PATH_IMPORT_CAR + request.getFileName());
        ViewFileValidation fileValidation = new ViewFileValidation();

        if (fileValidation.isValid(localFile)) {
            return toStringCarsFromFile(localFile);
        } else {
            return fileValidation.getUserErrorMessage();
        }
    }

    public String toStringCarsFromFile(LocalFile localFile) {
        List<Car> importedCarList = localFileService.importCarsFromFile(localFile, carModelMapper);
        if (importedCarList != null) {
            return toStringListCars(importedCarList);
        } else {
            return MessageConstant.EMPTY_CAR_FILE;
        }
    }

    public String toStringListCars(List<Car> carList) {
        StringBuilder result = new StringBuilder();
        for (Car car : carList) {
            result.append(carService.toStringCarInfo(car, packModelRepository));
        }
        return result.toString();
    }
}