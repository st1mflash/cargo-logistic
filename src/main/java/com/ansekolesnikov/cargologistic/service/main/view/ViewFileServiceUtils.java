package com.ansekolesnikov.cargologistic.service.main.view;

import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.car.utils.CarToStringUtils;
import com.ansekolesnikov.cargologistic.entity.file.LocalFile;
import com.ansekolesnikov.cargologistic.entity.file.utils.LocalFileImportUtils;
import com.ansekolesnikov.cargologistic.entity.file.utils.LocalFileUtils;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class ViewFileServiceUtils {
    @Autowired
    private CarToStringUtils carToStringUtils;
    @Autowired
    private LocalFileUtils localFileUtils;
    @Autowired
    private LocalFileImportUtils localFileImportUtils;
    private static final Logger LOGGER = Logger.getLogger(ViewFileServiceUtils.class.getName());

    public String getListCarsResultString(List<Car> carList) {
        StringBuilder result = new StringBuilder();
        for (Car car : carList) {
            result.append(carToStringUtils.toStringCarInfo(car));
        }
        return result.toString();
    }

    public String getCarsInfoFromFile(LocalFile localFile) {
        List<Car> importedCarList = localFileImportUtils.importCarsFromFile(localFile);
        if (importedCarList != null) {
            return getListCarsResultString(importedCarList);
        } else {
            LOGGER.info("Указанный файл '" + localFileUtils.calculateFilePathNameFormat(localFile) + "' не содержит информации о грузовиках");
            return "Указанный файл не содержит информации о грузовиках.";
        }
    }
}
