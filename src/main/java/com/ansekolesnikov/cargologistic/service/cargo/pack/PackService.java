package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.database.pack.InsertPackDatabase;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
@Getter
@Setter
public class PackService implements CargoService {
    InsertPackDatabase insertPackDatabase;
    private PackServiceUtils packServiceUtils = new PackServiceUtils();
    public PackService(
            InsertPackDatabase insertPackDatabase
    ) {
        this.insertPackDatabase = insertPackDatabase;
    }
    @Override
    public String runService(String params) {
        String operation = packServiceUtils.getPackOperationFromStringParams(params);
        switch (operation) {
            case "insert":
                createPack(
                        packServiceUtils.getPackNameFromStringParams(params),
                        packServiceUtils.getPackWidthFromStringParams(params),
                        packServiceUtils.getPackHeightFromStringParams(params),
                        packServiceUtils.getPackSchemeFromStringParams(params),
                        packServiceUtils.getPackCodeFromStringParams(params)
                );
                break;
            case "update":
                break;
            case "delete":
                break;
            default:
                break;
        }
        return null;
    }

    private void createPack(
            String namePack,
            String widthPack,
            String heightPack,
            String schemePack,
            String codePack
            ){
        Pack pack = new Pack(
                namePack,
                Integer.parseInt(widthPack),
                Integer.parseInt(heightPack),
                schemePack,
                codePack.charAt(0)
        );
        insertPackDatabase.insert(pack);
    }
}
