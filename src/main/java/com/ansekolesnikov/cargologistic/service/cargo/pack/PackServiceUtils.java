package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.model.command.pack.PackCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.Pack;

public class PackServiceUtils {

    public String getPackOperationFromStringParams(String inputParams){
        return inputParams.split(" ")[0];
    }

    private String getPackNameFromStringParams(String inputParams){
        return inputParams.split(" ")[2];
    }

    private String getPackCodeFromStringParams(String inputParams){
        return inputParams.split(" ")[3];
    }

    private String getPackWidthFromStringParams(String inputParams){
        return inputParams.split(" ")[4];
    }

    private String getPackHeightFromStringParams(String inputParams){
        return inputParams.split(" ")[5];
    }

    private String getPackSchemeFromStringParams(String inputParams){
        return inputParams.split(" ")[6];
    }

    public Pack createPackFromCommand(PackCommandLine command){
        return new Pack(
                command.getNamePack(),
                command.getWidthSchemePack(),
                command.getHeightSchemePack(),
                command.getSchemePack(),
                command.getCodePack()
                /*
                this.getPackNameFromStringParams(params),
                Integer.parseInt(this.getPackWidthFromStringParams(params)),
                Integer.parseInt(this.getPackHeightFromStringParams(params)),
                this.getPackSchemeFromStringParams(params),
                this.getPackCodeFromStringParams(params).charAt(0)
                */
        );
    }
}
