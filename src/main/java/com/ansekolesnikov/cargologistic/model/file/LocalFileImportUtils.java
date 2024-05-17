package com.ansekolesnikov.cargologistic.model.file;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
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
            return Arrays.stream(localFile.getContent().split("\\n\\s*\\n"))
                    .map(line -> String.valueOf(line.charAt(0) - 48))
                    .map(packModelDao::findByCode)
                    .map(Pack::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Ошибка ошибка импорта грузов из файла: '" + new LocalFileUtils().getFullAddress(localFile) + "': " + e);
            return null;
        }
    }

    public List<Car> importCarsFromFile(LocalFile localFile) {
        try {
            return new LocalFileUtils().getListJSONCars(localFile)
                    .stream()
                    .map(Car::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Ошибка импорта грузовиков из файла: '" + new LocalFileUtils().getFullAddress(localFile) + "': " + e);
            return null;
        }
    }
}
