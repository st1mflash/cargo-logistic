package com.ansekolesnikov.cargologistic.service.main.load;

import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.service.main.CargoService;
import com.ansekolesnikov.cargologistic.validation.service.LoadCarServiceValidation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadCarService implements CargoService {
    private static final String PATH_IMPORT_PACKAGES = "src/main/resources/import/packages/";

    public LoadCarService() {

    }

    @Override
    public String runService(String inputFileName, String inputAlgorithm, String inputCountCars) {
        LocalFile localFile = new LocalFile(PATH_IMPORT_PACKAGES + inputFileName);
        String algorithm = inputAlgorithm.toLowerCase();
        int countCars = Integer.parseInt(inputCountCars);

        LoadCarServiceValidation serviceValidation = new LoadCarServiceValidation(localFile, algorithm, countCars);
        LoadCarServiceUtils serviceUtils = new LoadCarServiceUtils();

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
