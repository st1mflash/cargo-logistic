package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.model.pack.Pack;
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
    public PackService(
            DatabaseService databaseService
    ) {
        this.databaseService = databaseService;
    }
    @Override
    public String runService(String params) {
        String operation = packServiceUtils.getPackOperationFromStringParams(params);
        switch (operation) {
            case "insert":
                return insertPackToDatabase(
                       packServiceUtils.createPackFromParams(params)
                );
            case "update":
                break;
            case "delete":
                break;
            default:
                break;
        }
        return null;
    }

    private String insertPackToDatabase(Pack pack){
        databaseService.getOperationsDatabase().getPackOperations().insert(pack);
        return "Посылка '" + pack.getName() + "' успешно создана.";
    }

    private String deletePackFromDatabase(Pack pack){
        return "Посылка '" + pack.getName() + "' успешно удалена.";
    }
}
