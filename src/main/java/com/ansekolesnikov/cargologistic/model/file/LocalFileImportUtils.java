package com.ansekolesnikov.cargologistic.model.file;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.Pack;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LocalFileImportUtils {
    private static final Logger LOGGER = Logger.getLogger(LocalFileImportUtils.class);

    public List<Pack> importPacksFromFile(LocalFile localFile) {
        try {
            return Arrays.stream(localFile.getContent().split("\\n\\s*\\n"))
                    .map(line -> line.charAt(0) - 48)
                    .map(Pack::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Ошибка ошибка импорта грузов из файла: '" + new LocalFileUtils().getFullAddress(localFile) + "': " + e);
            return null;
        }
    }

    public List<Car> importCarsFromFile(LocalFile localFile) {
        try {
            List<Car> listCars = new ArrayList<>();
            for (JSONObject JSONObj : new LocalFileUtils().getListJSONCars(localFile.getContent())) {
                listCars.add(new Car(JSONObj));
            }
            return listCars;
        } catch (Exception e) {
            LOGGER.error("Ошибка импорта грузовиков из файла: '" + new LocalFileUtils().getFullAddress(localFile) + "': " + e);
            return null;
        }
    }
}
