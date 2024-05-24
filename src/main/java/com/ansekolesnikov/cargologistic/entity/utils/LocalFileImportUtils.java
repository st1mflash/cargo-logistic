package com.ansekolesnikov.cargologistic.entity.utils;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Component
public class LocalFileImportUtils {
    @Autowired
    private LocalFileUtils localFileUtils;
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
            LOGGER.error("Ошибка ошибка импорта грузов из файла: '" + localFileUtils.calculateFilePathNameFormat(localFile) + "': " + e);
            return null;
        }
    }

    public List<Car> importCarsFromFile(LocalFile localFile) {
        try {
            return localFileUtils.importListJsonCars(localFile)
                    .stream()
                    .map(Car::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Ошибка импорта грузовиков из файла: '" + localFileUtils.calculateFilePathNameFormat(localFile) + "': " + e);
            return null;
        }
    }
}
