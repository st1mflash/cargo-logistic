package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.entity.RequestRunnableService;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.mappers.CarModelMapper;
import com.ansekolesnikov.cargologistic.mappers.LocalFileMapper;
import com.ansekolesnikov.cargologistic.mappers.LocalFileMapperImpl;
import com.ansekolesnikov.cargologistic.repository.PackModelRepository;
import com.ansekolesnikov.cargologistic.validation.ViewFileValidation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewFileService implements IRunnableByStringService {
    private final LocalFileMapper localFileMapper = new LocalFileMapperImpl();
    private final PackModelRepository packModelRepository;
    private final CarModelMapper carModelMapper;
    private final String PATH_IMPORT_CAR;

    public ViewFileService(
            PackModelRepository packModelRepository,
            CarModelMapper carModelMapper,
            @Value("${directory.car.import}") String pathImportCar
    ) {
        this.packModelRepository = packModelRepository;
        this.carModelMapper = carModelMapper;
        this.PATH_IMPORT_CAR = pathImportCar;
    }

    @Override
    public String run(RequestRunnableService request) {
        LocalFile localFile = localFileMapper.toLocalFile(PATH_IMPORT_CAR + request.getFileName());
        ViewFileValidation fileValidation = new ViewFileValidation(localFile);

        if (fileValidation.isValid()) {
            return toStringCarsFromFile(localFile);
        } else {
            return fileValidation.getUserErrorMessage();
        }
    }

    public String toStringCarsFromFile(LocalFile localFile) {
        List<Car> importedCarList = localFile.importCarsFromFile(carModelMapper);
        if (importedCarList != null) {
            return toStringListCars(importedCarList);
        } else {
            return "Указанный файл не содержит информации о грузовиках.";
        }
    }

    public String toStringListCars(List<Car> carList) {
        StringBuilder result = new StringBuilder();
        for (Car car : carList) {
            result.append(car.toStringCarInfo(packModelRepository));
        }
        return result.toString();
    }
}