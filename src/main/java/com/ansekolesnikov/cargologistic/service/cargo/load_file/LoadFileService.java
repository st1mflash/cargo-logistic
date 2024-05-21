package com.ansekolesnikov.cargologistic.service.cargo.load_file;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.entity.command.load_file.LoadFileCommandLine;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.file.LocalFile;
import com.ansekolesnikov.cargologistic.service.cargo.RunnableService;
import com.ansekolesnikov.cargologistic.validation.service.LoadFileCargoServiceValidation;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Setter
@Service
public class LoadFileService implements RunnableService {
    @Autowired
    private PackModelDao packModelDao;
    @Autowired
    private LoadFileServiceUtils loadFileServiceUtils;
    private String pathImportPackage;
    private LoadFileCommandLine loadFileCommandLine;

    public LoadFileService(String pathImportPackage) {
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
            List<Pack> importedPackList = loadFileServiceUtils.importPacksFromFileSortedByWidth(packModelDao, localFile);
            List<Car> loadedCarList = loadFileServiceUtils.loadCars(importedPackList, countCars, algorithm);

            if (serviceValidation.isValidCountCars(loadedCarList)) {
                return loadFileServiceUtils.toStringCarsInfo(loadedCarList);
            } else {
                return serviceValidation.getUserErrorMessage();
            }
        } else {
            return serviceValidation.getUserErrorMessage();
        }
    }
}
