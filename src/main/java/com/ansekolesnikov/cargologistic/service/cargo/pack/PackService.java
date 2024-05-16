package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.pack.PackCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import com.ansekolesnikov.cargologistic.model.pack.utils.PackToStringUtils;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
@Getter
@Setter
public class PackService implements CargoService {
    DatabaseService databaseService;
    private PackCommandLine packCommandLine;
    private PackServiceUtils packServiceUtils = new PackServiceUtils();
    private PackToStringUtils packToStringUtils = new PackToStringUtils();

    public PackService(
            DatabaseService databaseService
    ) {
        this.databaseService = databaseService;
    }

    @Override
    public String runService(CommandLine command) {
        packCommandLine = command.getPackCommandLine();
        return switch (packCommandLine.getOperation()) {
            case "insert" -> insertPackIntoDatabase(
                    packServiceUtils.createPackFromCommand(packCommandLine)
            );
            case "update" -> updatePackInDatabase(
                    findPackByIdInDatabase(
                            packCommandLine.getIdPack()
                    ),
                    packCommandLine
            );
            case "delete" -> deletePackFromDatabase(
                    findPackByIdInDatabase(
                            packCommandLine.getIdPack()
                    )
            );
            default -> null;
        };
    }

    private PackModel findPackByIdInDatabase(int id) {
        return databaseService
                .getOperationsDatabase()
                .getPackOperations()
                .queryById(id)
                ;
    }

    private String insertPackIntoDatabase(PackModel packModel) {
        databaseService.getOperationsDatabase().getPackOperations().insert(packModel);
        return "Посылка '" + packModel.getName() + "' успешно создана.\n\n"
                + packToStringUtils.toStringPackInfo(packModel);
    }

    private String updatePackInDatabase(PackModel packModel, PackCommandLine command) {
        PackModel updatedPackModel = packModel;
        switch (command.getParamName()) {
            case "name":
                updatedPackModel.setName(command.getParamValue());
                break;
            case "code":
                updatedPackModel.setCode(command.getParamValue().charAt(0));
                break;
            case "scheme":
                updatedPackModel.setScheme(command.getParamValue());
                break;
            case "scheme_width":
                updatedPackModel.setWidth(Integer.parseInt(command.getParamValue()));
                break;
            case "scheme_height":
                updatedPackModel.setHeight(Integer.parseInt(command.getParamValue()));
                break;
            default:
                break;
        }
        databaseService.getOperationsDatabase().getPackOperations().update(updatedPackModel);
        return "Посылка '" + packModel.getName() + "' успешно обновлена.\n\n"
                + packToStringUtils.toStringPackInfo(packModel);
    }

    private String deletePackFromDatabase(PackModel packModel) {
        databaseService.getOperationsDatabase().getPackOperations().delete(packModel);
        return "Посылка '" + packModel.getName() + "' успешно удалена.";
    }
}
