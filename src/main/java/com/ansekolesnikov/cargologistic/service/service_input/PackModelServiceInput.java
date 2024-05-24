package com.ansekolesnikov.cargologistic.service.service_input;

import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.enums.PackModelParameterEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class PackModelServiceInput extends ServiceInput {
    private DatabaseOperationEnum operation;
    private String namePack;
    private Character codePack;
    private String schemePack;
    private int widthSchemePack;
    private int heightSchemePack;
    private int idPack;
    private PackModelParameterEnum updatedParamName;
    private String updatedParamValue;
    private String text;
    public PackModelServiceInput(String command) {
        try {
            text = command;
            operation = DatabaseOperationEnum.initEnumFromString(command.split(" ")[1]);
            switch (operation) {
                case GET, DELETE:
                    idPack = Integer.parseInt(command.split(" ")[2]);
                    break;
                case INSERT:
                    namePack = command.split(" ")[2];
                    codePack = command.split(" ")[3].charAt(0);
                    schemePack = command.split(" ")[4];
                    widthSchemePack = Integer.parseInt(command.split(" ")[5]);
                    heightSchemePack = Integer.parseInt(command.split(" ")[6]);
                    break;
                case UPDATE:
                    idPack = Integer.parseInt(command.split(" ")[2]);
                    updatedParamName = PackModelParameterEnum.initEnumFromString(command.split(" ")[3]);
                    updatedParamValue = command.split(" ")[4];
                    break;
                default:
                    break;
            }
        } catch (RuntimeException ignored) {}
    }
}