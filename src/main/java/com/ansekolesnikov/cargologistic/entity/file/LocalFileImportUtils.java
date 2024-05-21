package com.ansekolesnikov.cargologistic.entity.file;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LocalFileImportUtils {
    private static final Logger LOGGER = Logger.getLogger(LocalFileImportUtils.class);

    public List<Pack> importPacksFromFile(
            PackModelDao packModelDao,
            LocalFile localFile
    ) {
        try {
            return Arrays
                    .stream(localFile.getContent().split("\\n\\s*\\n"))
                    .map(c -> c.replaceAll(" ", "").charAt(0))
                    .map(packModelDao::findByCode)
                    .map(Pack::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Ошибка ошибка импорта грузов из файла: '" + new LocalFileUtils().calculateFilePathNameFormat(localFile) + "': " + e);
            return null;
        }
    }

    public List<Car> importCarsFromFile(LocalFile localFile) {
        try {
            return new LocalFileUtils().importListJsonCars(localFile)
                    .stream()
                    .map(Car::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Ошибка импорта грузовиков из файла: '" + new LocalFileUtils().calculateFilePathNameFormat(localFile) + "': " + e);
            return null;
        }
    }
}