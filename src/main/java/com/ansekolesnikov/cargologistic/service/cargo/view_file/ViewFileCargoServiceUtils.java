package com.ansekolesnikov.cargologistic.service.cargo.view_file;

import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.car.utils.CarToStringUtils;
import com.ansekolesnikov.cargologistic.entity.file.LocalFile;
import com.ansekolesnikov.cargologistic.entity.file.LocalFileImportUtils;
import com.ansekolesnikov.cargologistic.entity.file.LocalFileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewFileCargoServiceUtils {
    private static final Logger LOGGER = Logger.getLogger(ViewFileCargoServiceUtils.class.getName());

    public String getListCarsResultString(List<Car> carList) {
        StringBuilder result = new StringBuilder();
        for (Car car : carList) {
            result.append(new CarToStringUtils().toStringCarInfo(car));
        }
        return result.toString();
    }

    public String getCarsInfoFromFile(LocalFile localFile) {
        List<Car> importedCarList = new LocalFileImportUtils().importCarsFromFile(localFile);
        if (importedCarList != null) {
            return new ViewFileCargoServiceUtils().getListCarsResultString(importedCarList);
        } else {
            LOGGER.info("Указанный файл '" + new LocalFileUtils().calculateFilePathNameFormat(localFile) + "' не содержит информации о грузовиках");
            return "Указанный файл не содержит информации о грузовиках.";
        }
    }
}
