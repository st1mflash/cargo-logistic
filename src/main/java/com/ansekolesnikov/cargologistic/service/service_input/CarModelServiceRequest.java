package com.ansekolesnikov.cargologistic.service.service_input;

import com.ansekolesnikov.cargologistic.enums.CarModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class CarModelServiceRequest extends ServiceRequest {
    private DatabaseOperationEnum operation;
    private String nameCar;
    private int widthSchemeCargoCar;
    private int heightSchemeCargoCar;
    private int idCar;
    private CarModelParameterEnum updatedParamName;
    private String updatedParamValue;
    private String text;

    public CarModelServiceRequest(String command) {
        try {
            text = command;
            operation = DatabaseOperationEnum.initEnumFromString(command.split(" ")[1]);
            switch (operation) {
                case GET, DELETE:
                    idCar = Integer.parseInt(command.split(" ")[2]);
                    break;
                case INSERT:
                    nameCar = command.split(" ")[2];
                    widthSchemeCargoCar = Integer.parseInt(command.split(" ")[3]);
                    heightSchemeCargoCar = Integer.parseInt(command.split(" ")[4]);
                    break;
                case UPDATE:
                    idCar = Integer.parseInt(command.split(" ")[2]);
                    updatedParamName = CarModelParameterEnum.initEnumFromString(command.split(" ")[3]);
                    updatedParamValue = command.split(" ")[4];
                    break;
                default:
                    break;
            }
        } catch (RuntimeException ignored) {
        }
    }
}
