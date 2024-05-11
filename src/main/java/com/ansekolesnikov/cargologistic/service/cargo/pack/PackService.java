package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.pack.PackCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.model.pack.PackUtils;
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
    private PackServiceUtils packServiceUtils = new PackServiceUtils();
    private PackUtils packUtils = new PackUtils();
    private PackCommandLine packCommandLine;

    public PackService(
            DatabaseService databaseService
    ) {
        this.databaseService = databaseService;
    }

    @Override
    public String runService(String params) {
        return null;
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

    private Pack findPackByIdInDatabase(int id) {
        return databaseService
                .getOperationsDatabase()
                .getPackOperations()
                .queryById(id)
                ;
    }

    private String insertPackIntoDatabase(Pack pack) {
        databaseService.getOperationsDatabase().getPackOperations().insert(pack);
        return "Посылка '" + pack.getName() + "' успешно создана.\n\n"
                + packUtils.toStringPackInfo(pack);
    }

    private String updatePackInDatabase(Pack pack, PackCommandLine command) {
        Pack updatedPack = pack;
        switch (command.getParamName()) {
            case "name":
                updatedPack.setName(command.getParamValue());
                break;
            case "code":
                updatedPack.setCode(command.getParamValue().charAt(0));
                break;
            case "scheme":
                updatedPack.setScheme(command.getParamValue());
                break;
            case "scheme_width":
                updatedPack.setWidth(Integer.parseInt(command.getParamValue()));
                break;
            case "scheme_height":
                updatedPack.setHeight(Integer.parseInt(command.getParamValue()));
                break;
            default:
                break;
        }
        databaseService.getOperationsDatabase().getPackOperations().update(updatedPack);
        return "Посылка '" + pack.getName() + "' успешно обновлена.\n\n"
                + packUtils.toStringPackInfo(pack);
    }

    private String deletePackFromDatabase(Pack pack) {
        databaseService.getOperationsDatabase().getPackOperations().delete(pack);
        return "Посылка '" + pack.getName() + "' успешно удалена.";
    }
}
