package com.ansekolesnikov.cargologistic.service.main.load.list;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.entity.command.load_list.LoadListCommandLine;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import com.ansekolesnikov.cargologistic.service.main.RunnableService;
import com.ansekolesnikov.cargologistic.service.main.ResultServiceRun;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class LoadListService implements RunnableService {
    @Autowired
    private CarModelDao carModelDao;
    @Autowired
    private PackModelDao packModelDao;
    @Autowired
    private LoadListServiceUtils loadListServiceUtils;

    private static final Logger LOGGER = Logger.getLogger(LoadListService.class.getName());

    @Override
    public ResultServiceRun runService(CommandLine commandLine) {
        ResultServiceRun result = new ResultServiceRun();
        try {
            LoadListCommandLine command = commandLine.getLoadListCommandLine();
            CarModel carModel = carModelDao.findByName(command.getCarModel());
            List<Pack> pack =
                    loadListServiceUtils
                            .createPacksByNameFromDatabase(
                                    packModelDao,
                                    command.getPacks()
                            );

            result.getResultLoadListServiceRun().setStringResult(
                    loadListServiceUtils.toStringCarsPacksInfo(
                            pack,
                            loadListServiceUtils.loadCars(
                                    carModel,
                                    pack,
                                    command.getCountCars(),
                                    command.getAlgorithm()
                            )
                    ));
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды.");
            result.getResultLoadListServiceRun().setStringResult("Ошибка ввода.");
            return result;
        }
    }
}
