package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import org.springframework.stereotype.Service;

@Service
public class PackService implements CargoService {
    private PackServiceUtils packServiceUtils = new PackServiceUtils();
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

    private String createPack(
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
        System.out.println("WOOOOOOW");
        return "";
    }
}
