package com.ansekolesnikov.cargologistic.service.cargo.car;

import com.ansekolesnikov.cargologistic.database.car_model.CarModelDao;
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

            case "insert" -> insertCarIntoDatabase(
                    carServiceUtils.createCarModelFromCommand(carCommandLine)
            );

            case "update" -> updateCarInDatabase(
                    findCarByIdInDatabase(
                            carCommandLine.getIdCar()
                    ),
                    carCommandLine
            );
            case "delete" -> deleteCarFromDatabase(
                    findCarByIdInDatabase(
                            carCommandLine.getIdCar()
                    )
            );
            default -> null;
        };
    }

    private CarModel findCarByIdInDatabase(int id) {
        return carModelDao.findById(id);
    }

    private String insertCarIntoDatabase(CarModel carModel) {
        carModelDao.insert(carModel);
        return "Грузовик '" + carModel.getNameModel() + "' успешно создан.\n\n"
                + carModelToStringUtils.toStringCarModelInfo(carModel);
    }

    private String updateCarInDatabase(CarModel carModel, CarCommandLine command) {
        CarModel updatedCarModel = carModel;
        switch (command.getParamName()) {
            case "name":
                updatedCarModel.setNameModel(command.getParamValue());
                break;
            case "cargo_width":
                updatedCarModel.setCargoWidthModel(Integer.parseInt(command.getParamValue()));
                break;
            case "cargo_height":
                updatedCarModel.setCargoHeightModel(Integer.parseInt(command.getParamValue()));
                break;
            default:
                break;
        }
        carModelDao.update(updatedCarModel);
        return "Грузовик '" + carModel.getNameModel() + "' успешно обновлен.\n\n"
                + carModelToStringUtils.toStringCarModelInfo(carModel);
    }

    private String deleteCarFromDatabase(CarModel carModel) {
        carModelDao.delete(carModel);
        return "Грузовик '" + carModel.getNameModel() + "' успешно удален.";
    }
}
