package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.entity.pack.PackModel;
import com.ansekolesnikov.cargologistic.entity.pack.utils.PackModelToStringUtils;
import com.ansekolesnikov.cargologistic.service.cargo.EntityService;
import com.ansekolesnikov.cargologistic.service.cargo.RunnableService;
import com.ansekolesnikov.cargologistic.service.cargo.car.CarService;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class PackService implements RunnableService, EntityService {
    @Autowired
    private PackServiceUtils packServiceUtils;
    @Autowired
    private PackModelToStringUtils packModelToStringUtils;
    private static final Logger LOGGER = Logger.getLogger(PackService.class.getName());

    @Override
    public String runService(CommandLine command) {
        try {
            return switch (command.getPackCommandLine().getOperation()) {
                case LIST -> listOperation();
                case INSERT -> insertOperation(command);
                case UPDATE -> updateOperation(command);
                case DELETE -> deleteOperation(command);
            };
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды. Текст команды: " + command.getPackCommandLine().getText());
            return "Ошибка ввода.\n" +
                    "Проверьте правильность введенной операции (доступные: INSERT/UPDATE/DELETE/LIST).";
        }
    }

    @Override
    public String listOperation() {
        return packServiceUtils.queryAllPackModelsToString();
    }

    @Override
    public String insertOperation(CommandLine command) {
        PackModel packModel = packServiceUtils.insertPackModelByCommand(command);
        return "Посылка '" + packModel.getName() + "' успешно создана.\n\n"
                + packModelToStringUtils.toStringPackInfo(packModel);
    }

    @Override
    public String updateOperation(CommandLine command) {
        PackModel packModel = packServiceUtils.updatePackModelByCommand(command);
        return "Посылка '" + packModel.getName() + "' успешно обновлена.\n\n"
                + packModelToStringUtils.toStringPackInfo(packModel);
    }

    @Override
    public String deleteOperation(CommandLine command) {
        PackModel packModel = packServiceUtils.deletePackModelByCommand(command);
        return "Посылка '" + packModel.getName() + "' успешно удалена.";
    }
}
