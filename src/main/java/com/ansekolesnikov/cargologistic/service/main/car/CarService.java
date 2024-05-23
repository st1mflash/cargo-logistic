package com.ansekolesnikov.cargologistic.service.main.car;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.ResultServiceRun;
import com.ansekolesnikov.cargologistic.service.main.EntityService;
import com.ansekolesnikov.cargologistic.service.main.RunnableService;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class CarService implements RunnableService, EntityService {
    @Autowired
    private CarServiceUtils carServiceUtils;
    private static final Logger LOGGER = Logger.getLogger(CarService.class.getName());

    @Override
    public ResultServiceRun runService(CommandLine command) {
        try {
            return switch (command.getCarCommandLine().getOperation()) {
                case LIST -> listOperation();
                case GET -> getOperation(command);
                case INSERT -> insertOperation(command);
                case UPDATE -> updateOperation(command);
                case DELETE -> deleteOperation(command);
            };
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды. Текст команды: " + command.getCarCommandLine().getText());
            ResultServiceRun result = new ResultServiceRun();
            result.getResultCarServiceRun().setStringResult(
                    "Ошибка ввода.\n" +
                            "Проверьте правильность введенной операции (доступные: INSERT/UPDATE/DELETE/LIST)."
            );
            return result;
        }
    }

    @Override
    public ResultServiceRun listOperation() {
        ResultServiceRun resultServiceRun = new ResultServiceRun();
        resultServiceRun.getResultCarServiceRun().setListCarModel(carServiceUtils.queryAllCarModels());
        return resultServiceRun;
    }

    @Override
    public ResultServiceRun getOperation(CommandLine command) {
        ResultServiceRun resultServiceRun = new ResultServiceRun();
        resultServiceRun.getResultCarServiceRun().setCarModel(carServiceUtils.queryCarModelById(command));
        return resultServiceRun;
    }

    @Override
    public ResultServiceRun insertOperation(CommandLine command) {
        ResultServiceRun resultServiceRun = new ResultServiceRun();
        resultServiceRun.getResultCarServiceRun().setCarModel(carServiceUtils.insertCarModelByCommand(command));
        return resultServiceRun;
    }

    @Override
    public ResultServiceRun updateOperation(CommandLine command) {
        ResultServiceRun resultServiceRun = new ResultServiceRun();
        resultServiceRun.getResultCarServiceRun().setCarModel(carServiceUtils.updateCarModelByCommand(command));
        return resultServiceRun;
    }

    @Override
    public ResultServiceRun deleteOperation(CommandLine command) {
        ResultServiceRun resultServiceRun = new ResultServiceRun();
        resultServiceRun.getResultCarServiceRun().setCarModel(carServiceUtils.deleteCarModelByCommand(command));
        return resultServiceRun;
    }
}
