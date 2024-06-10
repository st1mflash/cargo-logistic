package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.CarModel;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.entity.PackModel;
import com.ansekolesnikov.cargologistic.entity.LoaderPackToCar;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.service_input.LoadListServiceRequest;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceRequest;
import com.ansekolesnikov.cargologistic.service.service_output.LoadListServiceOutput;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LoadListService implements RunnableService {
    private final CarModelDao carModelDao;
    private final PackModelDao packModelDao;
    private final LoaderPackToCar loaderPackToCar;

    private static final Logger LOGGER = Logger.getLogger(LoadListService.class.getName());

    public LoadListService(
            CarModelDao carModelDao,
            PackModelDao packModelDao,
            LoaderPackToCar loaderPackToCar
    ) {
        this.carModelDao = carModelDao;
        this.packModelDao = packModelDao;
        this.loaderPackToCar = loaderPackToCar;
    }

    @Override
    public ServiceOutput runService(ServiceRequest serviceRequest) {
        LoadListServiceOutput result = new LoadListServiceOutput();
        try {
            LoadListServiceRequest command = serviceRequest.getLoadListServiceInput();
            CarModel carModel = carModelDao.findByName(command.getCarModel());
            List<Pack> pack =
                    createPacksByNameFromDatabase(
                            packModelDao,
                            command.getPacks()
                    );

            result.setText(toStringCarsPacksInfo(
                    pack,
                    loadCars(
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

    public List<Car> loadCars(
            CarModel inputCarModel,
            List<Pack> inputPack,
            int inputCountCars,
            AlgorithmEnum inputAlgorithm
    ) {
        List<Car> listCars = new ArrayList<>();

        for (int i = 0; i < inputCountCars; i++) {
            Car car = new Car(inputCarModel);
            listCars.add(car);

            List<Pack> filteredPackList = inputPack.stream()
                    .filter(pack -> pack.getCarId() == 0)
                    .toList();

            for (Pack pack : filteredPackList) {
                loaderPackToCar.loadPackToCar(car, pack, inputAlgorithm);
            }
        }
        return listCars;
    }

    public String toStringCarsPacksInfo(List<Pack> pack, List<Car> listCars) {
        StringBuilder result = new StringBuilder();

        for (PackModel packModel : pack) {
            result.append(packModel.getCode()).append(" -- ").append(packModel.getName()).append("\n");
        }
        result.append("\n");

        if (listCars != null) {
            for (Car car : listCars) {
                result.append(car.toStringCarCargoScheme()).append("\n");
            }
        }
        return result.toString();
    }

    public List<Pack> createPacksByNameFromDatabase(
            PackModelDao packModelDao,
            String[] packNames
    ) {
        return Arrays
                .stream(packNames)
                .map(packModelDao::findByName)
                .map(Pack::new)
                .toList();
    }
}
