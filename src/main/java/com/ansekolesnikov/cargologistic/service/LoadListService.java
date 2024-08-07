package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.constants.MessageConstant;
import com.ansekolesnikov.cargologistic.entity.*;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.mappers.CarModelMapper;
import com.ansekolesnikov.cargologistic.mappers.PackModelMapper;
import com.ansekolesnikov.cargologistic.repository.CarModelRepository;
import com.ansekolesnikov.cargologistic.repository.PackModelRepository;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoadListService implements IRunnableByStringService {
    private final CarModelRepository carModelRepository;
    private final PackModelRepository packModelRepository;
    private final LoaderPackToCarSelectorService loaderPackToCarSelectorService;
    private final CarModelMapper carModelMapper;
    private final PackModelMapper packModelMapper;
    private final CarService carService;

    private static final Logger LOGGER = Logger.getLogger(LoadListService.class.getName());

    @Override
    public String run(RequestString request) {
        try {
            CarModelEntity carModelEntity = carModelRepository.findByName(request.getEntityName());
            List<Pack> packs =
                    createPacksByNameFromDatabase(pullPacksNameListFromString(request.getRequest()));

            return toStringCarsPacksInfo(
                    packs,
                    loadCars(
                            carModelEntity,
                            packs,
                            request.getCountCars(),
                            request.getAlgorithm()
                    )
            );
        } catch (RuntimeException e) {
            LOGGER.error(MessageConstant.COMMAND_ERROR + e);
            return MessageConstant.COMMAND_ERROR;
        }
    }

    private List<Car> loadCars(
            CarModelEntity inputCarModelEntity,
            List<Pack> inputPack,
            int inputCountCars,
            AlgorithmEnum inputAlgorithm
    ) {
        List<Car> listCars = new ArrayList<>();

        for (int i = 0; i < inputCountCars; i++) {
            Car car = carModelMapper.toCar(inputCarModelEntity);
            listCars.add(car);

            List<Pack> filteredPackList = inputPack.stream()
                    .filter(pack -> pack.getCarId() == 0)
                    .toList();

            for (Pack pack : filteredPackList) {
                loaderPackToCarSelectorService.loadPackToCar(car, pack, inputAlgorithm);
            }
        }
        return listCars;
    }

    private String toStringCarsPacksInfo(List<Pack> pack, List<Car> listCars) {
        StringBuilder result = new StringBuilder();

        for (PackModelEntity packModelEntity : pack) {
            result.append(packModelEntity.getCode()).append(" -- ").append(packModelEntity.getName()).append("\n");
        }
        result.append("\n");

        if (listCars != null) {
            for (Car car : listCars) {
                result.append(carService.toStringCarCargoScheme(car)).append("\n");
            }
        }
        return result.toString();
    }

    private List<Pack> createPacksByNameFromDatabase(
            String[] packNames
    ) {
        return Arrays
                .stream(packNames)
                .map(packModelRepository::findByName)
                .map(packModelMapper::toPack)
                .toList();
    }

    private String[] pullPacksNameListFromString(String request) {
        return Arrays.copyOfRange(
                request.trim()
                        .split(" "),
                4,
                request.trim()
                        .split(" ")
                        .length
        );
    }
}
