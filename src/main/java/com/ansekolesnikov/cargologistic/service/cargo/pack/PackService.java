package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import com.ansekolesnikov.cargologistic.model.pack.utils.PackModelToStringUtils;
import com.ansekolesnikov.cargologistic.service.cargo.EntityService;
import com.ansekolesnikov.cargologistic.service.cargo.RunnableService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class PackService implements RunnableService, EntityService {
    @Autowired
    private PackServiceUtils packServiceUtils = new PackServiceUtils();
    @Autowired
    private PackModelToStringUtils packModelToStringUtils = new PackModelToStringUtils();

    @Override
    public String runService(CommandLine command) {
        return switch (command.getPackCommandLine().getOperation()) {
            case LIST -> listOperation();
            case INSERT -> insertOperation(command);
            case UPDATE -> updateOperation(command);
            case DELETE -> deleteOperation(command);
        };
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
