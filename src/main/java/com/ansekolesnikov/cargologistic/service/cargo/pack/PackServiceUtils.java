package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.model.pack.Pack;

public class PackServiceUtils {

    public String getPackOperationFromStringParams(String inputParams){
        return inputParams.split(" ")[0];
    }

    public String getPackNameFromStringParams(String inputParams){
        return inputParams.split(" ")[1];
    }

    public String getPackCodeFromStringParams(String inputParams){
        return inputParams.split(" ")[2];
    }

    public String getPackWidthFromStringParams(String inputParams){
        return inputParams.split(" ")[3];
    }

    public String getPackHeightFromStringParams(String inputParams){
        return inputParams.split(" ")[4];
    }

    public String getPackSchemeFromStringParams(String inputParams){
        return inputParams.split(" ")[5];
    }

    public Pack createPackFromParams(String params){
        return new Pack(
                this.getPackNameFromStringParams(params),
                Integer.parseInt(this.getPackWidthFromStringParams(params)),
                Integer.parseInt(this.getPackHeightFromStringParams(params)),
                this.getPackSchemeFromStringParams(params),
                this.getPackCodeFromStringParams(params).charAt(0)
        );
    }
}
