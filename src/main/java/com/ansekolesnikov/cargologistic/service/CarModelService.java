package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.dto.CarModelDto;
import com.ansekolesnikov.cargologistic.interfaces.EntityService;
import com.ansekolesnikov.cargologistic.interfaces.ICarModelService;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceRequest;
import com.ansekolesnikov.cargologistic.service.service_output.CarModelServiceOutput;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CarModelService implements
        IRunnableByStringService,
        EntityService,
        ICarModelService {
    private final CarModelDao carModelDao;

    private static final Logger LOGGER = Logger.getLogger(CarModelService.class.getName());

    @Override
    public ServiceOutput runService(ServiceRequest command) {
        try {
            return switch (command.getCarModelServiceInput().getOperation()) {
                case LIST -> listOperation();               //
                case GET -> getOperation(command);          //
                case INSERT -> insertOperation(command);
                case UPDATE -> updateOperation(command);
                case DELETE -> deleteOperation(command);    //
            };
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды. Текст команды: " + command.getCarModelServiceInput().getText());
            CarModelServiceOutput serviceOutput = new CarModelServiceOutput();
            serviceOutput.setText(
                    "Ошибка ввода.\n" +
                            "Проверьте правильность введенной операции (доступные: INSERT/UPDATE/DELETE/LIST)."
            );
            return serviceOutput;
        }
    }

    @Override
    public ServiceOutput listOperation() {
        /*
        CarModelServiceOutput serviceOutput = new CarModelServiceOutput();
        serviceOutput.create(carModelDao.findAll());
        */
        return null;
    }

    @Override
    public ServiceOutput getOperation(ServiceRequest command) {
        /*
        CarModelServiceOutput serviceOutput = new CarModelServiceOutput();
        serviceOutput.create(
                carModelDao.findById(command.getCarModelServiceInput().getIdCar())
        );
        */
        return null;
    }

    @Override
    public ServiceOutput insertOperation(ServiceRequest command) {
        /*
        CarModelServiceOutput serviceOutput = new CarModelServiceOutput();
        CarModelEntity carModelEntity = CarModelEntity.builder()
                .name(command.getCarModelServiceInput().getNameCar())
                .width(command.getCarModelServiceInput().getWidthSchemeCargoCar())
                .height(command.getCarModelServiceInput().getHeightSchemeCargoCar())
                .build();
        carModelDao.insert(carModelEntity);
        serviceOutput.create(carModelEntity);
        */
        return null;
    }


    @Override
    public ServiceOutput updateOperation(ServiceRequest command) {
        /*
        CarModelServiceOutput serviceOutput = new CarModelServiceOutput();
        CarModelEntity carModelEntity = carModelDao.findById(command.getCarModelServiceInput().getIdCar());
        switch (command.getCarModelServiceInput().getUpdatedParamName()) {
            case NAME:
                carModelEntity.setName(command.getCarModelServiceInput().getUpdatedParamValue());
                break;
            case WIDTH:
                carModelEntity.setWidth(Integer.parseInt(command.getCarModelServiceInput().getUpdatedParamValue()));
                break;
            case HEIGHT:
                carModelEntity.setHeight(Integer.parseInt(command.getCarModelServiceInput().getUpdatedParamValue()));
                break;
            default:
                break;
        }
        carModelDao.update(carModelEntity);
        serviceOutput.create(carModelEntity);
        */
        return null;
    }

    @Override
    public ServiceOutput deleteOperation(ServiceRequest command) {
        /*
        CarModelServiceOutput serviceOutput = new CarModelServiceOutput();
        CarModelEntity carModelEntity = carModelDao.findById(command.getCarModelServiceInput().getIdCar());
        carModelDao.delete(carModelEntity);
        serviceOutput.create(carModelEntity);
        */
        return null;
    }

    @Override
    public CarModelDto getCarModel(int id) {
        return carModelDao.findById(id);
    }

    @Override
    public List<CarModelDto> getCarModelList() {
        return carModelDao.findAll();
    }

    @Override
    public CarModelDto addCarModel(CarModelDto carModelDto) {
        return carModelDao.insert(carModelDto);
    }

    @Override
    public CarModelDto updateCarModel(CarModelDto carModelDto) {
        return carModelDao.update(carModelDto);
    }

    @Override
    public Map<String, String> deleteCarModel(int id) {
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
}
