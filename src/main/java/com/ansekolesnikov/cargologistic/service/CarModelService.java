package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import com.ansekolesnikov.cargologistic.interfaces.EntityService;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.service_output.CarModelServiceOutput;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CarModelService implements RunnableService, EntityService {
    @Autowired
    private CarModelDao carModelDao;
    private static final Logger LOGGER = Logger.getLogger(CarModelService.class.getName());

    @Override
    public ServiceOutput runService(ServiceInput command) {
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
            CarModelServiceOutput result = new CarModelServiceOutput();
            result.setText(
                    "Ошибка ввода.\n" +
                            "Проверьте правильность введенной операции (доступные: INSERT/UPDATE/DELETE/LIST)."
            );
            return result;
        }
    }

    @Override
    public ServiceOutput listOperation() {
        CarModelServiceOutput resultServiceRun = new CarModelServiceOutput();
        resultServiceRun.fillByListCarModel(carModelDao.findAll());
        return resultServiceRun;
    }

    @Override
    public ServiceOutput getOperation(ServiceInput command) {
        CarModelServiceOutput resultServiceRun = new CarModelServiceOutput();
        resultServiceRun.fillByCarModel(
                carModelDao.findById(command.getCarModelServiceInput().getIdCar())
        );
        return resultServiceRun;
    }

    @Override
    public ServiceOutput insertOperation(ServiceInput command) {
        CarModelServiceOutput resultServiceRun = new CarModelServiceOutput();
        CarModel carModel = new CarModel(
                command.getCarModelServiceInput().getNameCar(),
                command.getCarModelServiceInput().getWidthSchemeCargoCar(),
                command.getCarModelServiceInput().getHeightSchemeCargoCar()
        );
        carModelDao.insert(carModel);
        resultServiceRun.fillByCarModel(carModel);
        return resultServiceRun;
    }

    @Override
    public ServiceOutput updateOperation(ServiceInput command) {
        CarModelServiceOutput resultServiceRun = new CarModelServiceOutput();
        CarModel carModel = carModelDao.findById(command.getCarModelServiceInput().getIdCar());
        switch (command.getCarModelServiceInput().getUpdatedParamName()) {
            case NAME:
                carModel.setNameModel(command.getCarModelServiceInput().getUpdatedParamValue());
                break;
            case WIDTH:
                carModel.setCargoWidthModel(Integer.parseInt(command.getCarModelServiceInput().getUpdatedParamValue()));
                break;
            case HEIGHT:
                carModel.setCargoHeightModel(Integer.parseInt(command.getCarModelServiceInput().getUpdatedParamValue()));
                break;
            default:
                break;
        }
        carModelDao.update(carModel);
        resultServiceRun.fillByCarModel(carModel);
        return resultServiceRun;
    }

    @Override
    public ServiceOutput deleteOperation(ServiceInput command) {
        CarModelServiceOutput resultServiceRun = new CarModelServiceOutput();
        CarModel carModel = carModelDao.findById(command.getCarModelServiceInput().getIdCar());
        carModelDao.delete(carModel);
        resultServiceRun.fillByCarModel(carModel);
        return resultServiceRun;
    }
}
