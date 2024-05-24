package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import com.ansekolesnikov.cargologistic.interfaces.EntityService;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.service_output.CarModelServiceOutput;
import com.ansekolesnikov.cargologistic.service.utils.CarServiceUtils;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CarModelService implements RunnableService, EntityService {
    @Autowired
    private CarServiceUtils carServiceUtils;
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
        resultServiceRun.fillByListCarModel(carServiceUtils.queryAllCarModels());
        return resultServiceRun;
    }

    @Override
    public ServiceOutput getOperation(ServiceInput command) {
        CarModelServiceOutput resultServiceRun = new CarModelServiceOutput();
        resultServiceRun.fillByCarModel(carServiceUtils.queryCarModelById(command));
        return resultServiceRun;
    }

    @Override
    public ServiceOutput insertOperation(ServiceInput command) {
        CarModelServiceOutput resultServiceRun = new CarModelServiceOutput();
        resultServiceRun.fillByCarModel(carServiceUtils.insertCarModelByCommand(command));
        return resultServiceRun;
    }

    @Override
    public ServiceOutput updateOperation(ServiceInput command) {
        CarModelServiceOutput resultServiceRun = new CarModelServiceOutput();
        resultServiceRun.fillByCarModel(carServiceUtils.updateCarModelByCommand(command));
        return resultServiceRun;
    }

    @Override
    public ServiceOutput deleteOperation(ServiceInput command) {
        CarModelServiceOutput resultServiceRun = new CarModelServiceOutput();
        resultServiceRun.fillByCarModel(carServiceUtils.deleteCarModelByCommand(command));
        return resultServiceRun;
    }
}
