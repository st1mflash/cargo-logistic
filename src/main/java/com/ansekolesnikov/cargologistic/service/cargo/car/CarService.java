package com.ansekolesnikov.cargologistic.service.cargo.car;

import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.model.car.utils.CarModelToStringUtils;
import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.car.CarCommandLine;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CarService implements CargoService {
    @Autowired
    private CarServiceUtils carServiceUtils = new CarServiceUtils();
    @Autowired
    private CarModelToStringUtils carModelToStringUtils = new CarModelToStringUtils();

    @Override
    public String runService(CommandLine inputCommand) {
        CarCommandLine command = inputCommand.getCarCommandLine();
        return switch (command.getOperation()) {
            case LIST -> listOperation();
            case INSERT -> insertOperation(command);
            case UPDATE -> updateOperation(command);
            case DELETE -> deleteOperation(command);
        };
    }

    private String listOperation() {
        return carServiceUtils.queryAllCarModelsToString();
    }

    private String insertOperation(CarCommandLine command) {
        CarModel carModel = carServiceUtils.insertCarModelByCommand(command);
        return "Грузовик '" + carModel.getNameModel() + "' успешно создан.\n\n"
                + carModelToStringUtils.toStringCarModelInfo(carModel);
    }

    private String updateOperation(CarCommandLine command) {
        CarModel carModel = carServiceUtils.updateCarModelByCommand(command);
        return "Грузовик '" + carModel.getNameModel() + "' успешно обновлен.\n\n"
                + carModelToStringUtils.toStringCarModelInfo(carModel);
    }

    private String deleteOperation(CarCommandLine command) {
        CarModel carModel = carServiceUtils.deleteCarModelByCommand(command);
        return "Грузовик '" + carModel.getNameModel() + "' успешно удален.";
    }
}
