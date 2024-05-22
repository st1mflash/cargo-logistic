package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.entity.command.pack.PackCommandLine;
import com.ansekolesnikov.cargologistic.entity.pack.PackModel;
import com.ansekolesnikov.cargologistic.entity.pack.utils.PackModelToStringUtils;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class PackServiceUtils {
    @Autowired
    private PackModelDao packModelDao;
    @Autowired
    private PackModelToStringUtils packModelToStringUtils;

    public PackModel createPackModelFromCommand(PackCommandLine command) {
        return new PackModel(
                command.getNamePack(),
                command.getWidthSchemePack(),
                command.getHeightSchemePack(),
                command.getSchemePack(),
                command.getCodePack()
        );
    }

    public String queryAllPackModelsToString() {
        StringBuilder toStringCars = new StringBuilder();
        for (PackModel packModel : packModelDao.findAll()) {
            toStringCars
                    .append(packModelToStringUtils.toStringPackInfo(packModel))
                    .append("\n\n");
        }
        if (toStringCars.isEmpty()) {
            return "Список моделей посылок пуст." +
                    "\nДля добавления воспользуйтесь командой: 'pack insert [название] [код] [ширина] [высота]'";
        } else {
            return toStringCars.toString();
        }
    }

    public PackModel insertPackModelByCommand(CommandLine inputCommand) {
        PackCommandLine command = inputCommand.getPackCommandLine();
        PackModel packModel = createPackModelFromCommand(command);
        packModelDao.insert(packModel);
        return packModel;
    }

    public PackModel updatePackModelByCommand(CommandLine inputCommand) {
        PackCommandLine command = inputCommand.getPackCommandLine();
        PackModel packModel = packModelDao.findById(command.getIdPack());
        switch (command.getUpdatedParamName()) {
            case NAME:
                packModel.setName(command.getUpdatedParamValue());
                break;
            case CODE:
                packModel.setCode(command.getUpdatedParamValue().charAt(0));
                break;
            case SCHEME:
                packModel.setScheme(command.getUpdatedParamValue());
                break;
            case WIDTH:
                packModel.setWidth(Integer.parseInt(command.getUpdatedParamValue()));
                break;
            case HEIGHT:
                packModel.setHeight(Integer.parseInt(command.getUpdatedParamValue()));
                break;
            default:
                break;
        }
        packModelDao.update(packModel);
        return packModel;
    }

    public PackModel deletePackModelByCommand(CommandLine inputCommand) {
        PackCommandLine command = inputCommand.getPackCommandLine();
        PackModel packModel = packModelDao.findById(command.getIdPack());
        packModelDao.delete(packModel);
        return packModel;
    }
}
