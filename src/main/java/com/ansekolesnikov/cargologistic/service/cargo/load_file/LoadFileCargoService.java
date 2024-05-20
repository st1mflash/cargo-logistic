package com.ansekolesnikov.cargologistic.service.cargo.load_file;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.load_file.LoadFileCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import com.ansekolesnikov.cargologistic.validation.service.LoadFileCargoServiceValidation;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Setter
@Service
public class LoadFileCargoService implements CargoService {
    @Autowired
    private PackModelDao packModelDao;
    private String pathImportPackage;
    private LoadFileCargoServiceUtils loadFileCargoServiceUtils;
    private LoadFileCommandLine loadFileCommandLine;
    private DatabaseService databaseService;

    public LoadFileCargoService(
            DatabaseService databaseService,
            LoadFileCargoServiceUtils loadFileCargoServiceUtils,
            String pathImportPackage
    ) {
        this.databaseService = databaseService;
        this.loadFileCargoServiceUtils = loadFileCargoServiceUtils;
        this.pathImportPackage = pathImportPackage;
    }

    @Override
    public String runService(CommandLine commandLine) {
        loadFileCommandLine = commandLine.getLoadFileCommandLine();
        LocalFile localFile = new LocalFile(pathImportPackage + loadFileCommandLine.getFileName());
        AlgorithmEnum algorithm = loadFileCommandLine.getAlgorithm();
        int countCars = loadFileCommandLine.getCountCars();

        LoadFileCargoServiceValidation serviceValidation = new LoadFileCargoServiceValidation(localFile, algorithm, countCars);

        if (serviceValidation.isValid()) {
            List<Pack> importedPackList = loadFileCargoServiceUtils.importPacksFromFileSortedByWidth(packModelDao, localFile);
            List<Car> loadedCarList = loadFileCargoServiceUtils.loadCars(importedPackList, countCars, algorithm);

            if (serviceValidation.isValidCountCars(loadedCarList)) {
                return loadFileCargoServiceUtils.toStringCarsInfo(loadedCarList);
            } else {
                return serviceValidation.getUserErrorMessage();
            }
        } else {
            return serviceValidation.getUserErrorMessage();
        }
    }
}
