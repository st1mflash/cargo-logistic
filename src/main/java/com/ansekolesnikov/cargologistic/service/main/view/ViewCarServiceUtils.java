package com.ansekolesnikov.cargologistic.service.main.view;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarStringInfo;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.model.file.LocalFileImportUtils;
import com.ansekolesnikov.cargologistic.model.file.LocalFileUtils;
import org.apache.log4j.Logger;

import java.util.List;

public class ViewCarServiceUtils {
    private static final Logger LOGGER = Logger.getLogger(ViewCarServiceUtils.class.getName());
    public String getListCarsResultString(List<Car> carList) {
        StringBuilder result = new StringBuilder();
        for (Car car : carList) {
            result.append(new CarStringInfo().getFullInfo(car));
        }
        return result.toString();
    }
    public String getCarsInfoFromFile(LocalFile localFile) {
        List<Car> importedCarList = new LocalFileImportUtils().importCarsFromFile(localFile);
        if (importedCarList != null) {
            return new ViewCarServiceUtils().getListCarsResultString(importedCarList);
        } else {
            LOGGER.info("Указанный файл '" + new LocalFileUtils().getFullAddress(localFile) + "' не содержит информации о грузовиках");
            return "Указанный файл не содержит информации о грузовиках.";
        }
    }
}
