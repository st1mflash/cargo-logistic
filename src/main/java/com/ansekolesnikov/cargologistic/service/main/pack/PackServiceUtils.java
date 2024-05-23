package com.ansekolesnikov.cargologistic.service.main.pack;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.entity.command.pack.PackCommandLine;
import com.ansekolesnikov.cargologistic.entity.pack.PackModel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class PackServiceUtils {
    @Autowired
    private PackModelDao packModelDao;

    public List<PackModel> queryAllPackModels() {
        return packModelDao.findAll();
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

    public PackModel createPackModelFromCommand(PackCommandLine command) {
        return new PackModel(
                command.getNamePack(),
                command.getWidthSchemePack(),
                command.getHeightSchemePack(),
                command.getSchemePack(),
                command.getCodePack()
        );
    }
}
