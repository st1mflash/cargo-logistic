package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.annotations.CargoCar;
import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.dto.CarModelDto;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import com.ansekolesnikov.cargologistic.enums.CarModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.interfaces.ICarModelService;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CargoCar
@RequiredArgsConstructor
@Service
public class CarModelService implements
        IRunnableByStringService,
        ICarModelService {
    private final CarModelDao carModelDao;

    private static final Logger LOGGER = Logger.getLogger(CarModelService.class.getName());

    @Override
    public CarModelDto getCarModel(int id) {
        LOGGER.info("Запрос информации о модели посылки.");
        return carModelDao.findById(id);
    }

    @Override
    public List<CarModelDto> getCarModelList() {
        LOGGER.info("Запрос информации о всех моделях посылок.");
        return carModelDao.findAll();
    }

    @Override
    public CarModelDto addCarModel(CarModelDto carModelDto) {
        LOGGER.info("Добавление модели посылки.");
        return carModelDao.insert(carModelDto);
    }

    @Override
    public CarModelDto updateCarModel(CarModelDto carModelDto) {
        LOGGER.info("Обновление модели посылки.");
        return carModelDao.update(carModelDto);
    }

    @Override
    public Map<String, String> deleteCarModel(int id) {
        LOGGER.info("Удаление модели посылки.");
        Map<String, String> result = new HashMap<>();
        try {
            carModelDao.delete(id);
            result.put("status", "success");
            return result;
        } catch (RuntimeException e) {
            result.put("status", "failed");
            return result;
        }
    }

    @Override
    public String run(String request) {
        DatabaseOperationEnum operation = DatabaseOperationEnum.initEnumFromString(request.split(" ")[1]);
        switch (Objects.requireNonNull(operation)) {
            case LIST:
                StringBuilder carList = new StringBuilder();
                getCarModelList().stream()
                        .map(CarModelEntity::to)
                        .forEach(c -> carList.append(c).append("\n\n"));
                return carList.toString();
            case GET:
                return CarModelEntity.to(getCarModel(Integer.parseInt(request.split(" ")[2]))).toString();
            case INSERT:
                CarModelDto carModelDto = CarModelDto.builder()
                        .name(request.split(" ")[2])
                        .width(Integer.parseInt(request.split(" ")[3]))
                        .height(Integer.parseInt(request.split(" ")[4]))
                        .build();
                return CarModelEntity.to(addCarModel(carModelDto)).toString();
            case UPDATE:
                return CarModelEntity.to(updateCarByParams(request)).toString();
            case DELETE:
                deleteCarModel(Integer.parseInt(request.split(" ")[2]));
                return "Успешное удаление";
            default:
                return "Не удалось определить команду";
        }
    }

    private CarModelDto updateCarByParams(String commandString) {
        CarModelParameterEnum parameterEnum = CarModelParameterEnum.initEnumFromString(commandString.split(" ")[3]);
        String value = commandString.split(" ")[4];
        CarModelDto carModelDto = carModelDao.findById(Integer.parseInt(commandString.split(" ")[2]));
        switch (Objects.requireNonNull(parameterEnum)) {
            case NAME:
                carModelDto.setName(value);
                break;
            case WIDTH:
                carModelDto.setWidth(Integer.parseInt(value));
                break;
            case HEIGHT:
                carModelDto.setHeight(Integer.parseInt(value));
                break;
            default:
                break;
        }
        return updateCarModel(carModelDto);
    }
}
