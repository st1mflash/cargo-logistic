package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_input.LoadListServiceInput;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.utils.LoadListServiceUtils;
import com.ansekolesnikov.cargologistic.service.service_output.LoadListServiceOutput;
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
    public ServiceOutput runService(ServiceInput serviceInput) {
        LoadListServiceOutput result = new LoadListServiceOutput();
        try {
            LoadListServiceInput command = serviceInput.getLoadListServiceInput();
            CarModel carModel = carModelDao.findByName(command.getCarModel());
            List<Pack> pack =
                    loadListServiceUtils
                            .createPacksByNameFromDatabase(
                                    packModelDao,
                                    command.getPacks()
                            );

            result.setText(
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
            result.setText("Ошибка ввода.");
            return result;
        }
    }
}
