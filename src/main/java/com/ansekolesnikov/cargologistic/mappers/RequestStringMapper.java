package com.ansekolesnikov.cargologistic.mappers;

import com.ansekolesnikov.cargologistic.entity.RequestString;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.enums.CarModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.enums.PackModelParameterEnum;
import com.ansekolesnikov.cargologistic.service.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Mapper
public interface RequestStringMapper {
    default RequestString toRequestString(Class serviceClass, String request) {
        RequestString requestString = new RequestString();
        if (serviceClass == CarModelService.class) {
            initCarModelServiceRequestParams(requestString, request);
        } else if (serviceClass == PackModelService.class) {
            initPackModelServiceRequestParams(requestString, request);
        } else if (serviceClass == ViewFileService.class) {
            initViewFileServiceRequestParams(requestString, request);
        } else if (serviceClass == LoadFileService.class) {
            initLoadFileServiceRequestParams(requestString, request);
        } else if (serviceClass == LoadListService.class) {
            initLoadListServiceRequestParams(requestString, request);
        }
        return requestString;
    }

    private void initCarModelServiceRequestParams(RequestString requestString, String request) {
        requestString.setOperation(DatabaseOperationEnum.valueOf(request.split(" ")[1].toUpperCase()));
        switch (Objects.requireNonNull(requestString.getOperation())) {
            case GET, DELETE:
                requestString.setEntityId(Integer.parseInt(request.split(" ")[2]));
                break;
            case INSERT:
                requestString.setEntityName(request.split(" ")[2]);
                requestString.setEntityWidth(Integer.parseInt(request.split(" ")[3]));
                requestString.setEntityHeight(Integer.parseInt(request.split(" ")[4]));
                break;
            case UPDATE:
                requestString.setEntityId(Integer.parseInt(request.split(" ")[2]));
                requestString.setCarModelParameterName(CarModelParameterEnum.valueOf(request.split(" ")[3].toUpperCase()));
                requestString.setEntityParameterValue(request.split(" ")[4]);
                break;
        }
    }

    private void initPackModelServiceRequestParams(RequestString requestString, String request) {
        requestString.setOperation(DatabaseOperationEnum.valueOf(request.split(" ")[1].toUpperCase()));
        switch (Objects.requireNonNull(requestString.getOperation())) {
            case GET, DELETE:
                requestString.setEntityId(Integer.parseInt(request.split(" ")[2]));
                break;
            case INSERT:
                requestString.setEntityName(request.split(" ")[2]);
                requestString.setEntityCode(request.split(" ")[3].charAt(0));
                requestString.setEntityScheme(request.split(" ")[4]);
                requestString.setEntityWidth(Integer.parseInt(request.split(" ")[5]));
                requestString.setEntityHeight(Integer.parseInt(request.split(" ")[6]));
                break;
            case UPDATE:
                requestString.setEntityId(Integer.parseInt(request.split(" ")[2]));
                requestString.setPackModelParameterName(PackModelParameterEnum.valueOf(request.split(" ")[3].toUpperCase()));
                requestString.setEntityParameterValue(request.split(" ")[4]);
                break;
        }
    }

    private void initViewFileServiceRequestParams(RequestString requestString, String request) {
        requestString.setFileName(request.split(" ")[1]);
    }

    private void initLoadFileServiceRequestParams(RequestString requestString, String request) {
        requestString.setFileName(request.split(" ")[1]);
        requestString.setAlgorithm(AlgorithmEnum.valueOf(request.split(" ")[2].toUpperCase()));
        requestString.setCountCars(Integer.parseInt(request.split(" ")[3]));
    }

    private void initLoadListServiceRequestParams(RequestString requestString, String request) {
        requestString.setEntityName(request.split(" ")[1]);
        requestString.setAlgorithm(AlgorithmEnum.valueOf(request.split(" ")[2].toUpperCase()));
        requestString.setCountCars(Integer.parseInt(String.valueOf(request.split(" ")[3].charAt(0))));
        requestString.setPackModelNameList(new String[][]{request
                .substring(request.indexOf(":") + 1)
                .trim()
                .split("\\n")});
    }
}
