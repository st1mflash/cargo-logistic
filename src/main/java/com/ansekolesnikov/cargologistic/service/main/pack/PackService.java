package com.ansekolesnikov.cargologistic.service.main.pack;

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
public class PackService implements RunnableService, EntityService {
    @Autowired
    private PackServiceUtils packServiceUtils;
    private static final Logger LOGGER = Logger.getLogger(PackService.class.getName());

    @Override
    public ResultServiceRun runService(CommandLine command) {
        try {
            return switch (command.getPackCommandLine().getOperation()) {
                case LIST -> listOperation();
                case GET -> getOperation(command);
                case INSERT -> insertOperation(command);
                case UPDATE -> updateOperation(command);
                case DELETE -> deleteOperation(command);
            };
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды. Текст команды: " + command.getPackCommandLine().getText());
            ResultPackServiceRun result = new ResultPackServiceRun();
            result.setText(
                    "Ошибка ввода.\n" +
                            "Проверьте правильность введенной операции (доступные: INSERT/UPDATE/DELETE/LIST)."
            );
            return result;
        }
    }

    @Override
    public ResultServiceRun listOperation() {
        ResultPackServiceRun resultServiceRun = new ResultPackServiceRun();
        resultServiceRun.fillByListPackModel(packServiceUtils.queryAllPackModels());
        return resultServiceRun;
    }

    @Override
    public ResultServiceRun getOperation(CommandLine command) {
        ResultPackServiceRun resultServiceRun = new ResultPackServiceRun();
        resultServiceRun.fillByPackModel(packServiceUtils.queryPackModelById(command));
        return resultServiceRun;
    }

    @Override
    public ResultServiceRun insertOperation(CommandLine command) {
        ResultPackServiceRun resultServiceRun = new ResultPackServiceRun();
        resultServiceRun.fillByPackModel(packServiceUtils.insertPackModelByCommand(command));
        return resultServiceRun;
    }

    @Override
    public ResultServiceRun updateOperation(CommandLine command) {
        ResultPackServiceRun resultServiceRun = new ResultPackServiceRun();
        resultServiceRun.fillByPackModel(packServiceUtils.updatePackModelByCommand(command));
        return resultServiceRun;
    }

    @Override
    public ResultServiceRun deleteOperation(CommandLine command) {
        ResultPackServiceRun resultServiceRun = new ResultPackServiceRun();
        resultServiceRun.fillByPackModel(packServiceUtils.deletePackModelByCommand(command));
        return resultServiceRun;
    }
}
