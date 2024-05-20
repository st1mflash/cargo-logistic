package com.ansekolesnikov.cargologistic.service.cargo.car;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.model.car.utils.CarModelToStringUtils;
import com.ansekolesnikov.cargologistic.model.car.utils.CarUtils;
import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.car.CarCommandLine;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
@Getter
@Setter
public class CarService implements CargoService {
    @Autowired
    private CarModelDao carModelDao;
    private CarCommandLine carCommandLine;
    private CarServiceUtils carServiceUtils = new CarServiceUtils();
    private CarModelToStringUtils carModelToStringUtils = new CarModelToStringUtils();
    private CarUtils carUtils = new CarUtils();

    @Override
    public String runService(CommandLine command) {
        carCommandLine = command.getCarCommandLine();
        return switch (carCommandLine.getOperation()) {
            case LIST -> toStringAllCarModelsFromDatabase();
            case INSERT -> insertCarIntoDatabase(carServiceUtils.createCarModelFromCommand(carCommandLine));
            case UPDATE -> updateCarInDatabase(findCarByIdInDatabase(carCommandLine.getIdCar()), carCommandLine);
            case DELETE -> deleteCarFromDatabase(findCarByIdInDatabase(carCommandLine.getIdCar()));
        };
    }

    private CarModel findCarByIdInDatabase(int id) {
        return carModelDao.findById(id);
    }

    private String toStringAllCarModelsFromDatabase() {
        StringBuilder toStringCars = new StringBuilder();
        for (CarModel carModel : carModelDao.findAll()) {
            toStringCars
                    .append(carModelToStringUtils.toStringCarModelInfo(carModel))
                    .append("\n\n");
        }
        if (toStringCars.isEmpty()) {
            return "Список моделей грузовиков пуст." +
                    "\nДля добавления воспользуйтесь командой: 'car insert [название] [ширина] [высота]'";
        } else {
            return toStringCars.toString();
        }
    }

    private String insertCarIntoDatabase(CarModel carModel) {
        carModelDao.insert(carModel);
        return "Грузовик '" + carModel.getNameModel() + "' успешно создан.\n\n"
                + carModelToStringUtils.toStringCarModelInfo(carModel);
    }

    private String updateCarInDatabase(CarModel carModel, CarCommandLine command) {
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
        return "Грузовик '" + carModel.getNameModel() + "' успешно обновлен.\n\n"
                + carModelToStringUtils.toStringCarModelInfo(carModel);
    }

    private String deleteCarFromDatabase(CarModel carModel) {
        carModelDao.delete(carModel);
        return "Грузовик '" + carModel.getNameModel() + "' успешно удален.";
    }
}
