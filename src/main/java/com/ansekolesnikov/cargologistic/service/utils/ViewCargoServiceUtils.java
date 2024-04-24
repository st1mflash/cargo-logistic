package com.ansekolesnikov.cargologistic.service.utils;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarStringInfoUtils;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.model.file.LocalFileImportUtils;
import com.ansekolesnikov.cargologistic.model.file.LocalFileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewCargoServiceUtils extends ServiceUtils {
    private static final Logger LOGGER = Logger.getLogger(ViewCargoServiceUtils.class.getName());

    public String getListCarsResultString(List<Car> carList) {
        StringBuilder result = new StringBuilder();
        for (Car car : carList) {
            result.append(new CarStringInfoUtils().getFullInfo(car));
        }
        return result.toString();
    }

    public String getCarsInfoFromFile(LocalFile localFile) {
        List<Car> importedCarList = new LocalFileImportUtils().importCarsFromFile(localFile);
        if (importedCarList != null) {
            return new ViewCargoServiceUtils().getListCarsResultString(importedCarList);
        } else {
            LOGGER.info("Указанный файл '" + new LocalFileUtils().getFullAddress(localFile) + "' не содержит информации о грузовиках");
            return "Указанный файл не содержит информации о грузовиках.";
        }
    }
}
