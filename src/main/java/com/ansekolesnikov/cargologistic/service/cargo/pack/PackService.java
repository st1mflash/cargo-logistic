package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.pack.PackCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import com.ansekolesnikov.cargologistic.model.pack.utils.PackModelToStringUtils;
import com.ansekolesnikov.cargologistic.service.cargo.RunnableService;
import com.ansekolesnikov.cargologistic.service.cargo.EntityService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class PackService implements RunnableService, EntityService {
    @Autowired
    private PackModelDao packModelDao;
    @Autowired
    private PackServiceUtils packServiceUtils = new PackServiceUtils();
    @Autowired
    private PackModelToStringUtils packModelToStringUtils = new PackModelToStringUtils();

    @Override
    public String runService(CommandLine command) {
        PackCommandLine packCommandLine = command.getPackCommandLine();
        return switch (packCommandLine.getOperation()) {
            case LIST -> toStringAllPackModelsFromDatabase();
            case INSERT -> insertPackIntoDatabase(packServiceUtils.createPackFromCommand(packCommandLine));
            case UPDATE -> updatePackInDatabase(findPackByIdInDatabase(packCommandLine.getIdPack()), packCommandLine);
            case DELETE -> deletePackFromDatabase(findPackByIdInDatabase(packCommandLine.getIdPack()));
        };
    }

    @Override
    public String listOperation(){
        return "";
    }

    @Override
    public String insertOperation(CommandLine command){
        return "";
    }

    @Override
    public String updateOperation(CommandLine command){
        return "";
    }

    @Override
    public String deleteOperation(CommandLine command){
        return "";
    }

    private PackModel findPackByIdInDatabase(int id) {
        return packModelDao.findById(id);
    }

    private String toStringAllPackModelsFromDatabase() {
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

    private String insertPackIntoDatabase(PackModel packModel) {
        packModelDao.insert(packModel);
        return "Посылка '" + packModel.getName() + "' успешно создана.\n\n"
                + packModelToStringUtils.toStringPackInfo(packModel);
    }

    private String updatePackInDatabase(PackModel packModel, PackCommandLine command) {
        PackModel updatedPackModel = packModel;
        switch (command.getUpdatedParamName()) {
            case NAME:
                updatedPackModel.setName(command.getUpdatedParamValue());
                break;
            case CODE:
                updatedPackModel.setCode(command.getUpdatedParamValue().charAt(0));
                break;
            case SCHEME:
                updatedPackModel.setScheme(command.getUpdatedParamValue());
                break;
            case WIDTH:
                updatedPackModel.setWidth(Integer.parseInt(command.getUpdatedParamValue()));
                break;
            case HEIGHT:
                updatedPackModel.setHeight(Integer.parseInt(command.getUpdatedParamValue()));
                break;
            default:
                break;
        }
        packModelDao.update(updatedPackModel);
        return "Посылка '" + packModel.getName() + "' успешно обновлена.\n\n"
                + packModelToStringUtils.toStringPackInfo(packModel);
    }

    private String deletePackFromDatabase(PackModel packModel) {
        packModelDao.delete(packModel);
        return "Посылка '" + packModel.getName() + "' успешно удалена.";
    }
}
