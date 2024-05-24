package com.ansekolesnikov.cargologistic.service.utils;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_input.CarModelServiceInput;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class CarServiceUtils {
    @Autowired
    private CarModelDao carModelDao;

    public CarModel queryCarModelById(ServiceInput inputCommand) {
        CarModelServiceInput command = inputCommand.getCarModelServiceInput();
        return carModelDao.findById(command.getIdCar());
    }

    public List<CarModel> queryAllCarModels() {
        return carModelDao.findAll();
    }

    public CarModel insertCarModelByCommand(ServiceInput inputCommand) {
        CarModelServiceInput command = inputCommand.getCarModelServiceInput();
        CarModel carModel = createCarModelFromCommand(command);
        carModelDao.insert(carModel);
        return carModel;
    }

    public CarModel updateCarModelByCommand(ServiceInput inputCommand) {
        CarModelServiceInput command = inputCommand.getCarModelServiceInput();
        CarModel carModel = carModelDao.findById(command.getIdCar());
        switch (command.getUpdatedParamName()) {
            case NAME:
                carModel.setNameModel(command.getUpdatedParamValue());
                break;
            case WIDTH:
                carModel.setCargoWidthModel(Integer.parseInt(command.getUpdatedParamValue()));
                break;
            case HEIGHT:
                carModel.setCargoHeightModel(Integer.parseInt(command.getUpdatedParamValue()));
                break;
            default:
                break;
        }
        carModelDao.update(carModel);
        return carModel;
    }

    public CarModel deleteCarModelByCommand(ServiceInput inputCommand) {
        CarModelServiceInput command = inputCommand.getCarModelServiceInput();
        CarModel carModel = carModelDao.findById(command.getIdCar());
        carModelDao.delete(carModel);
        return carModel;
    }

    private CarModel createCarModelFromCommand(CarModelServiceInput command) {
        return new CarModel(
                command.getNameCar(),
                command.getWidthSchemeCargoCar(),
                command.getHeightSchemeCargoCar()
        );
    }
}
