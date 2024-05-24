package com.ansekolesnikov.cargologistic.service.utils;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_input.PackModelServiceInput;
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

    public PackModel queryPackModelById(ServiceInput inputCommand) {
        PackModelServiceInput command = inputCommand.getPackModelServiceInput();
        return packModelDao.findById(command.getIdPack());
    }

    public PackModel insertPackModelByCommand(ServiceInput inputCommand) {
        PackModelServiceInput command = inputCommand.getPackModelServiceInput();
        PackModel packModel = createPackModelFromCommand(command);
        packModelDao.insert(packModel);
        return packModel;
    }

    public PackModel updatePackModelByCommand(ServiceInput inputCommand) {
        PackModelServiceInput command = inputCommand.getPackModelServiceInput();
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

    public PackModel deletePackModelByCommand(ServiceInput inputCommand) {
        PackModelServiceInput command = inputCommand.getPackModelServiceInput();
        PackModel packModel = packModelDao.findById(command.getIdPack());
        packModelDao.delete(packModel);
        return packModel;
    }

    public PackModel createPackModelFromCommand(PackModelServiceInput command) {
        return new PackModel(
                command.getNamePack(),
                command.getWidthSchemePack(),
                command.getHeightSchemePack(),
                command.getSchemePack(),
                command.getCodePack()
        );
    }
}
