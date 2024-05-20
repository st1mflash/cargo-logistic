package com.ansekolesnikov.cargologistic.service.cargo.car;

import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.model.car.utils.CarModelToStringUtils;
import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.cargo.RunnableService;
import com.ansekolesnikov.cargologistic.service.cargo.EntityService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CarService implements RunnableService, EntityService {
    @Autowired
    private CarServiceUtils carServiceUtils = new CarServiceUtils();
    @Autowired
    private CarModelToStringUtils carModelToStringUtils = new CarModelToStringUtils();

    @Override
    public String runService(CommandLine command) {
        return switch (command.getCarCommandLine().getOperation()) {
            case LIST -> listOperation();
            case INSERT -> insertOperation(command);
            case UPDATE -> updateOperation(command);
            case DELETE -> deleteOperation(command);
        };
    }

    @Override
    public String listOperation() {
        return carServiceUtils.queryAllCarModelsToString();
    }

    @Override
    public String insertOperation(CommandLine command) {
        CarModel carModel = carServiceUtils.insertCarModelByCommand(command);
        return "Грузовик '" + carModel.getNameModel() + "' успешно создан.\n\n"
                + carModelToStringUtils.toStringCarModelInfo(carModel);
    }

    @Override
    public String updateOperation(CommandLine command) {
        CarModel carModel = carServiceUtils.updateCarModelByCommand(command);
        return "Грузовик '" + carModel.getNameModel() + "' успешно обновлен.\n\n"
                + carModelToStringUtils.toStringCarModelInfo(carModel);
    }

    @Override
    public String deleteOperation(CommandLine command) {
        CarModel carModel = carServiceUtils.deleteCarModelByCommand(command);
        return "Грузовик '" + carModel.getNameModel() + "' успешно удален.";
    }
}
