package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import com.ansekolesnikov.cargologistic.interfaces.EntityService;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.utils.PackServiceUtils;
import com.ansekolesnikov.cargologistic.service.service_output.PackModelServiceOutput;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class PackModelService implements RunnableService, EntityService {
    @Autowired
    private PackServiceUtils packServiceUtils;
    private static final Logger LOGGER = Logger.getLogger(PackModelService.class.getName());

    @Override
    public ServiceOutput runService(ServiceInput command) {
        try {
            return switch (command.getPackModelServiceInput().getOperation()) {
                case LIST -> listOperation();
                case GET -> getOperation(command);
                case INSERT -> insertOperation(command);
                case UPDATE -> updateOperation(command);
                case DELETE -> deleteOperation(command);
            };
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды. Текст команды: " + command.getPackModelServiceInput().getText());
            PackModelServiceOutput result = new PackModelServiceOutput();
            result.setText(
                    "Ошибка ввода.\n" +
                            "Проверьте правильность введенной операции (доступные: INSERT/UPDATE/DELETE/LIST)." + e
            );
            return result;
        }
    }

    @Override
    public ServiceOutput listOperation() {
        PackModelServiceOutput resultServiceRun = new PackModelServiceOutput();
        resultServiceRun.fillByListPackModel(packServiceUtils.queryAllPackModels());
        return resultServiceRun;
    }

    @Override
    public ServiceOutput getOperation(ServiceInput command) {
        PackModelServiceOutput resultServiceRun = new PackModelServiceOutput();
        resultServiceRun.fillByPackModel(packServiceUtils.queryPackModelById(command));
        return resultServiceRun;
    }

    @Override
    public ServiceOutput insertOperation(ServiceInput command) {
        PackModelServiceOutput resultServiceRun = new PackModelServiceOutput();
        resultServiceRun.fillByPackModel(packServiceUtils.insertPackModelByCommand(command));
        return resultServiceRun;
    }

    @Override
    public ServiceOutput updateOperation(ServiceInput command) {
        PackModelServiceOutput resultServiceRun = new PackModelServiceOutput();
        resultServiceRun.fillByPackModel(packServiceUtils.updatePackModelByCommand(command));
        return resultServiceRun;
    }

    @Override
    public ServiceOutput deleteOperation(ServiceInput command) {
        PackModelServiceOutput resultServiceRun = new PackModelServiceOutput();
        resultServiceRun.fillByPackModel(packServiceUtils.deletePackModelByCommand(command));
        return resultServiceRun;
    }
}
