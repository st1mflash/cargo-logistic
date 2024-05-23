package com.ansekolesnikov.cargologistic.service.main.load.file;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.car.Car;
import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.entity.command.load_file.LoadFileCommandLine;
import com.ansekolesnikov.cargologistic.entity.file.LocalFile;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.service.main.ResultServiceRun;
import com.ansekolesnikov.cargologistic.service.main.RunnableService;
import com.ansekolesnikov.cargologistic.validation.service.LoadFileServiceValidation;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
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
    private LoadFileServiceUtils utils;
    private String pathImportPackage;

    private static final Logger LOGGER = Logger.getLogger(LoadFileService.class.getName());

    public LoadFileService(String pathImportPackage) {
        this.pathImportPackage = pathImportPackage;
    }

    @Override
    public ResultServiceRun runService(CommandLine commandLine) {
        ResultLoadFileServiceRun result = new ResultLoadFileServiceRun();
        try {
            LoadFileCommandLine command = commandLine.getLoadFileCommandLine();
            LocalFile file = new LocalFile(
                    pathImportPackage + command.getFileName()
            );
            AlgorithmEnum algorithm = command.getAlgorithm();
            int countCars = command.getCountCars();

            LoadFileServiceValidation validation = new LoadFileServiceValidation(
                    file,
                    algorithm,
                    countCars
            );

            if (validation.isValid()) {
                List<Pack> importedPackList = utils
                        .importPacksFromFileSortedByWidth(
                                packModelDao,
                                file
                        );
                List<Car> loadedCarList = utils
                        .loadCars(
                                importedPackList,
                                countCars,
                                algorithm
                        );

                if (validation.isValidCountCars(loadedCarList)) {
                    result.setText(utils.toStringCarsInfo(loadedCarList));
                } else {
                    result.setText(validation.getUserErrorMessage());
                }
            } else {
                result.setText(validation.getUserErrorMessage());
            }
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды.");
            result.setText("Ошибка ввода.");
            return result;
        }
    }
}
