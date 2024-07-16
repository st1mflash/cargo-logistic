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
    private final ViewFileValidation viewFileValidation;

    @Override
    public String run(RequestString request) {
        LocalFile localFile = localFileMapper.toLocalFile(PATH_IMPORT_CAR + request.getFileName());
        return (viewFileValidation.isValid(localFile) ? toStringCarsFromFile(localFile) : viewFileValidation.getUserErrorMessage());
    }

    private String toStringCarsFromFile(LocalFile localFile) {
        List<Car> importedCarList = localFileService.importCarsFromFile(localFile, carModelMapper);
        return (localFileService.importCarsFromFile(localFile, carModelMapper) != null ? toStringListCars(importedCarList) : MessageConstant.EMPTY_CAR_FILE);
    }

    private String toStringListCars(List<Car> carList) {
        StringBuilder result = new StringBuilder();
        carList.forEach(car -> result.append(carService.toStringCarInfo(car, packModelRepository)));
        return result.toString();
    }
}