package com.ansekolesnikov.cargologistic.service.main.pack;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.result.ResultServiceRun;
import com.ansekolesnikov.cargologistic.service.main.EntityService;
import com.ansekolesnikov.cargologistic.service.main.RunnableService;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class PackService implements RunnableService, EntityService {
    @Autowired
    private PackServiceUtils packServiceUtils;
    private static final Logger LOGGER = Logger.getLogger(PackService.class.getName());

    @Override
    public ResultServiceRun runService(CommandLine command) {
        try {
            return switch (command.getPackCommandLine().getOperation()) {
                case LIST -> listOperation();
                case INSERT -> insertOperation(command);
                case UPDATE -> updateOperation(command);
                case DELETE -> deleteOperation(command);
            };
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды. Текст команды: " + command.getPackCommandLine().getText());
            ResultServiceRun result = new ResultServiceRun();
            result.getResultPackServiceRun().setStringResult(
                    "Ошибка ввода.\n" +
                            "Проверьте правильность введенной операции (доступные: INSERT/UPDATE/DELETE/LIST)."
            );
            return result;
        }
    }

    @Override
    public ResultServiceRun listOperation() {
        ResultServiceRun resultServiceRun = new ResultServiceRun();
        resultServiceRun.getResultPackServiceRun().setListPackModel(packServiceUtils.queryAllPackModels());
        return resultServiceRun;
    }

    @Override
    public ResultServiceRun insertOperation(CommandLine command) {
        ResultServiceRun resultServiceRun = new ResultServiceRun();
        resultServiceRun.getResultPackServiceRun().setPackModel(packServiceUtils.insertPackModelByCommand(command));
        return resultServiceRun;
    }

    @Override
    public ResultServiceRun updateOperation(CommandLine command) {
        ResultServiceRun resultServiceRun = new ResultServiceRun();
        resultServiceRun.getResultPackServiceRun().setPackModel(packServiceUtils.updatePackModelByCommand(command));
        return resultServiceRun;
    }

    @Override
    public ResultServiceRun deleteOperation(CommandLine command) {
        ResultServiceRun resultServiceRun = new ResultServiceRun();
        resultServiceRun.getResultPackServiceRun().setPackModel(packServiceUtils.deletePackModelByCommand(command));
        return resultServiceRun;
    }
}
