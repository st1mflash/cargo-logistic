package com.ansekolesnikov.cargologistic.service.cargo.pack;

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
}
