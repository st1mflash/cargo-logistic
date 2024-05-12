package com.ansekolesnikov.cargologistic.service.cargo.load_file;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Setter
@Service
public class LoadFileCargoService implements CargoService {
    @Value("${directory.pack.import}")
    private String PATH_IMPORT_PACKAGE;
    @Autowired
    LoadFileCargoServiceUtils serviceUtils;
    private LoadFileCommandLine loadFileCommandLine;
    @Autowired
    DatabaseService databaseService;

    @Override
    public String runService(CommandLine commandLine) {
        loadFileCommandLine = commandLine.getLoadFileCommandLine();
        LocalFile localFile = new LocalFile(PATH_IMPORT_PACKAGE + loadFileCommandLine.getFileName());
        String algorithm = loadFileCommandLine.getAlgorithm();
        int countCars = loadFileCommandLine.getCountCars();

        LoadFileCargoServiceValidation serviceValidation = new LoadFileCargoServiceValidation(localFile, algorithm, countCars);

        if (serviceValidation.isValid()) {
            List<Pack> importedPackList = serviceUtils.getListPacksFromFile(databaseService, localFile);
            List<Car> loadedCarList = serviceUtils.loadCars(importedPackList, countCars, algorithm);

            if (serviceValidation.isValidCountCars(loadedCarList)) {
                return serviceUtils.getCarsInfo(loadedCarList);
            } else {
                return serviceValidation.getUserErrorMessage();
            }
        } else {
            return serviceValidation.getUserErrorMessage();
        }
    }
}
