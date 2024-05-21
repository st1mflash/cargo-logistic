package com.ansekolesnikov.cargologistic.service.cargo.car;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import com.ansekolesnikov.cargologistic.entity.car.utils.CarModelToStringUtils;
import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.entity.command.car.CarCommandLine;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class CarServiceUtils {
    @Autowired
    private CarModelDao carModelDao;

    public String queryAllCarModelsToString() {
        StringBuilder toStringCars = new StringBuilder();
        for (CarModel carModel : carModelDao.findAll()) {
            toStringCars
                    .append(new CarModelToStringUtils().toStringCarModelInfo(carModel))
                    .append("\n\n");
        }
        if (toStringCars.isEmpty()) {
            return "Список моделей грузовиков пуст." +
                    "\nДля добавления воспользуйтесь командой: 'car insert [название] [ширина] [высота]'";
        } else {
            return toStringCars.toString();
        }
    }

    public CarModel insertCarModelByCommand(CommandLine inputCommand) {
        CarCommandLine command = inputCommand.getCarCommandLine();
        CarModel carModel = createCarModelFromCommand(command);
        carModelDao.insert(carModel);
        return carModel;
    }

    public CarModel updateCarModelByCommand(CommandLine inputCommand) {
        CarCommandLine command = inputCommand.getCarCommandLine();
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

    public CarModel deleteCarModelByCommand(CommandLine inputCommand) {
        CarCommandLine command = inputCommand.getCarCommandLine();
        CarModel carModel = carModelDao.findById(command.getIdCar());
        carModelDao.delete(carModel);
        return carModel;
    }

    private CarModel createCarModelFromCommand(CarCommandLine command) {
        return new CarModel(
                command.getNameCar(),
                command.getWidthSchemeCargoCar(),
                command.getHeightSchemeCargoCar()
        );
    }
}
