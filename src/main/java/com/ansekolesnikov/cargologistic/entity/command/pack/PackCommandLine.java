package com.ansekolesnikov.cargologistic.entity.command.pack;

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
public class PackCommandLine {
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
    public PackCommandLine(String command) {
        text = command;
        operation = DatabaseOperationEnum.initEnumFromString(command.split(" ")[1]);
        switch (operation) {
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
            case DELETE:
                idPack = Integer.parseInt(command.split(" ")[2]);
                break;
            default:
                break;
        }
    }
}
