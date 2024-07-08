package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import com.ansekolesnikov.cargologistic.enums.CarModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.enums.PackModelParameterEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestString {
    private String request;

    private DatabaseOperationEnum operation;
    private int entityId;
    private String entityName;
    private Character entityCode;
    private String entityScheme;
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
}
