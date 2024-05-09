package com.ansekolesnikov.cargologistic.service.cargo.load;

import com.ansekolesnikov.cargologistic.database.car.QueryCarDatabase;
import com.ansekolesnikov.cargologistic.database.pack.QueryPackDatabase;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import com.ansekolesnikov.cargologistic.validation.service.LoadCarServiceValidation;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Setter
@Service
public class LoadCargoService implements CargoService {
    @Value("${directory.pack.import}")
    private String PATH_IMPORT_PACKAGE;
    @Autowired
    LoadCargoServiceUtils serviceUtils;

    QueryPackDatabase queryPackDatabase;
    QueryCarDatabase queryCarDatabase;

    @Override
    public String runService(String params) {
        LocalFile localFile = new LocalFile(PATH_IMPORT_PACKAGE + serviceUtils.getFileNameFromStringParams(params));
        String algorithm = serviceUtils.getAlgorithmFromStringParams(params);
        int countCars = serviceUtils.getCountCarsFromStringParams(params);

        LoadCarServiceValidation serviceValidation = new LoadCarServiceValidation(localFile, algorithm, countCars);

        if (serviceValidation.isValid()) {
            List<Pack> importedPackList = serviceUtils.getListPacksFromFile(localFile);
            List<Car> loadedCarList = serviceUtils.loadCars(importedPackList, countCars, algorithm);

            if (serviceValidation.isValidCountCars(loadedCarList)) {
                return serviceUtils.getCarsInfo(loadedCarList);
            } else {
                return serviceValidation.getUserErrorMessage();
            }
        } else {
            return serviceValidation.getUserErrorMessage();
        }
    }
}
