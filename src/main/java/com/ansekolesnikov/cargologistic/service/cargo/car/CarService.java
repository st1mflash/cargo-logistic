package com.ansekolesnikov.cargologistic.service.cargo.car;

import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import com.ansekolesnikov.cargologistic.entity.car.utils.CarModelToStringUtils;
import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.cargo.EntityService;
import com.ansekolesnikov.cargologistic.service.cargo.RunnableService;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CarService implements RunnableService, EntityService {
    @Autowired
    private CarServiceUtils carServiceUtils;
    @Autowired
    private CarModelToStringUtils carModelToStringUtils;
    private static final Logger LOGGER = Logger.getLogger(CarService.class.getName());

    @Override
    public String runService(CommandLine command) {
        try {
            return switch (command.getCarCommandLine().getOperation()) {
                case LIST -> listOperation();
                case INSERT -> insertOperation(command);
                case UPDATE -> updateOperation(command);
                case DELETE -> deleteOperation(command);
            };
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды. Текст команды: " + command.getCarCommandLine().getText());
            return "Ошибка ввода.\n" +
                    "Проверьте правильность введенной операции (доступные: INSERT/UPDATE/DELETE/LIST).";
        }
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
