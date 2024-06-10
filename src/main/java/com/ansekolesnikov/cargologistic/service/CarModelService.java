package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.entity.CarModel;
import com.ansekolesnikov.cargologistic.interfaces.ICarModelService;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceRequest;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import com.ansekolesnikov.cargologistic.interfaces.EntityService;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.service_output.CarModelServiceOutput;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CarModelService implements
        RunnableService,
        EntityService,
        ICarModelService {
    private final CarModelDao carModelDao;

    private static final Logger LOGGER = Logger.getLogger(CarModelService.class.getName());

    @Override
    public ServiceOutput runService(ServiceRequest command) {
        try {
            return switch (command.getCarModelServiceInput().getOperation()) {
                case LIST -> listOperation();
                case GET -> getOperation(command);
                case INSERT -> insertOperation(command);
                case UPDATE -> updateOperation(command);
                case DELETE -> deleteOperation(command);
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
        CarModelServiceOutput serviceOutput = new CarModelServiceOutput();
        serviceOutput.create(carModelDao.findAll());
        return serviceOutput;
    }

    @Override
    public ServiceOutput getOperation(ServiceRequest command) {
        CarModelServiceOutput serviceOutput = new CarModelServiceOutput();
        serviceOutput.create(
                carModelDao.findById(command.getCarModelServiceInput().getIdCar())
        );
        return serviceOutput;
    }

    @Override
    public ServiceOutput insertOperation(ServiceRequest command) {
        CarModelServiceOutput serviceOutput = new CarModelServiceOutput();
        CarModel carModel = CarModel.builder()
                .name(command.getCarModelServiceInput().getNameCar())
                .width(command.getCarModelServiceInput().getWidthSchemeCargoCar())
                .height(command.getCarModelServiceInput().getHeightSchemeCargoCar())
                .build();
        carModelDao.insert(carModel);
        serviceOutput.create(carModel);
        return serviceOutput;
    }

    @Override
    public ServiceOutput updateOperation(ServiceRequest command) {
        CarModelServiceOutput serviceOutput = new CarModelServiceOutput();
        CarModel carModel = carModelDao.findById(command.getCarModelServiceInput().getIdCar());
        switch (command.getCarModelServiceInput().getUpdatedParamName()) {
            case NAME:
                carModel.setName(command.getCarModelServiceInput().getUpdatedParamValue());
                break;
            case WIDTH:
                carModel.setWidth(Integer.parseInt(command.getCarModelServiceInput().getUpdatedParamValue()));
                break;
            case HEIGHT:
                carModel.setHeight(Integer.parseInt(command.getCarModelServiceInput().getUpdatedParamValue()));
                break;
            default:
                break;
        }
        carModelDao.update(carModel);
        serviceOutput.create(carModel);
        return serviceOutput;
    }

    @Override
    public ServiceOutput deleteOperation(ServiceRequest command) {
        CarModelServiceOutput serviceOutput = new CarModelServiceOutput();
        CarModel carModel = carModelDao.findById(command.getCarModelServiceInput().getIdCar());
        carModelDao.delete(carModel);
        serviceOutput.create(carModel);
        return serviceOutput;
    }

    @Override
    public CarModel getCarModel(int id) {
        return carModelDao.findById(id);
    }

    @Override
    public List<CarModel> getCarModelList() {
        return carModelDao.findAll();
    }

    @Override
    public CarModel addCarModel(CarModel carModel) {
        return carModelDao.insert(carModel);
    }

    @Override
    public CarModel updateCarModel(CarModel carModel) {
        return carModelDao.update(carModel);
    }

    @Override
    public Map<String, String> deleteCarModel(CarModel carModel) {
        Map<String, String> result = new HashMap<>();
        try {
            carModelDao.delete(carModel);
            result.put("status", "success");
            return result;
        } catch (RuntimeException e) {
            result.put("status", "failed");
            return result;
        }
    }
}
