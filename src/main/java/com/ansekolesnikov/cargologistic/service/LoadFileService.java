package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_input.LoadFileServiceInput;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.utils.LoadFileServiceUtils;
import com.ansekolesnikov.cargologistic.service.service_output.LoadFileServiceOutput;
import com.ansekolesnikov.cargologistic.validation.service.LoadFileServiceValidation;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${directory.pack.import}")
    private String PATH_IMPORT_PACKAGE;
    private static final Logger LOGGER = Logger.getLogger(LoadFileService.class.getName());

    @Override
    public ServiceOutput runService(ServiceInput serviceInput) {
        LoadFileServiceOutput result = new LoadFileServiceOutput();
        try {
            LoadFileServiceInput command = serviceInput.getLoadFileServiceInput();
            LocalFile file = new LocalFile(
                    PATH_IMPORT_PACKAGE + command.getFileName()
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
