package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.enums.CarModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.enums.PackModelParameterEnum;
import com.ansekolesnikov.cargologistic.service.*;
import com.ansekolesnikov.cargologistic.utils.EntityUtils;
import lombok.Data;

import java.util.Objects;

@Data
public class RequestString {
    private String request;

    private DatabaseOperationEnum operation;
    private int entityId;
    private String entityName;
    private int entityWidth;
    private int entityHeight;
    private String entityParameterValue;

    private int countCars;
    private String fileName;
    private AlgorithmEnum algorithm;
    private String[][] packModelNameList;
    private String packModelScheme;
    private Character packModelCode;

    private CarModelParameterEnum carModelParameterName;
    private PackModelParameterEnum packModelParameterName;

    public RequestString(Class serviceClass, String request) {
        this.request = request;
        if (serviceClass == CarModelService.class) {
            initCarModelServiceRequestParams(request);
        } else if (serviceClass == PackModelService.class) {
            initPackModelServiceRequestParams(request);
        } else if (serviceClass == ViewFileService.class) {
            initViewFileServiceRequestParams(request);
        } else if (serviceClass == LoadFileService.class) {
            initLoadFileServiceRequestParams(request);
        } else if (serviceClass == LoadListService.class) {
            initLoadListServiceRequestParams(request);
        }

    }

    private void initCarModelServiceRequestParams(String request) {
        operation = EntityUtils.getDatabaseOperationEnum(request.split(" ")[1]);
        switch (Objects.requireNonNull(operation)) {
            case GET, DELETE:
                entityId = Integer.parseInt(request.split(" ")[2]);
                break;
            case INSERT:
                entityName = request.split(" ")[2];
                entityWidth = Integer.parseInt(request.split(" ")[3]);
                entityHeight = Integer.parseInt(request.split(" ")[4]);
                break;
            case UPDATE:
                entityId = Integer.parseInt(request.split(" ")[2]);
                carModelParameterName = EntityUtils.getCarModelParameterEnum(request.split(" ")[3]);
                entityParameterValue = request.split(" ")[4];
                break;
        }
    }

    private void initPackModelServiceRequestParams(String request) {
        operation = EntityUtils.getDatabaseOperationEnum(request.split(" ")[1]);
        switch (Objects.requireNonNull(operation)) {
            case GET, DELETE:
                entityId = Integer.parseInt(request.split(" ")[2]);
                break;
            case INSERT:
                entityName = request.split(" ")[2];
                entityWidth = Integer.parseInt(request.split(" ")[3]);
                entityHeight = Integer.parseInt(request.split(" ")[4]);
                break;
            case UPDATE:
                entityId = Integer.parseInt(request.split(" ")[2]);
                packModelParameterName = EntityUtils.getPackModelParameterEnum(request.split(" ")[3]);
                entityParameterValue = request.split(" ")[4];
                break;
        }
    }

    private void initViewFileServiceRequestParams(String request) {
        fileName = request.split(" ")[1];
    }

    private void initLoadFileServiceRequestParams(String request) {
        fileName = request.split(" ")[1];
        algorithm = EntityUtils.getAlgorithmEnum(request.split(" ")[2]);
        countCars = Integer.parseInt(request.split(" ")[3]);
    }

    private void initLoadListServiceRequestParams(String request) {
        entityName = request.split(" ")[1];
        algorithm = EntityUtils.getAlgorithmEnum(request.split(" ")[2]);
        countCars = Integer.parseInt(String.valueOf(request.split(" ")[3].charAt(0)));
        packModelNameList = new String[][]{request
                .substring(request.indexOf(":") + 1)
                .trim()
                .split("\\n")};
    }
}
