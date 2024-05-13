package com.ansekolesnikov.cargologistic.model.command.pack;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class PackCommandLine {
    private String operation;
    private String namePack;
    private Character codePack;
    private String schemePack;
    private int widthSchemePack;
    private int heightSchemePack;
    private int idPack;
    private String paramName;
    private String paramValue;
    private String text;
    public PackCommandLine(String command) {
        this.text = command;
        this.operation = command.split(" ")[1].toLowerCase();
        switch (this.operation) {
            case "insert":
                this.namePack = command.split(" ")[2];
                this.codePack = command.split(" ")[3].charAt(0);
                this.schemePack = command.split(" ")[4];
                this.widthSchemePack = Integer.parseInt(command.split(" ")[5]);
                this.heightSchemePack = Integer.parseInt(command.split(" ")[6]);
                break;
            case "update":
                this.idPack = Integer.parseInt(command.split(" ")[2]);
                this.paramName = command.split(" ")[3].toLowerCase();
                this.paramValue = command.split(" ")[4];
                break;
            case "delete":
                this.idPack = Integer.parseInt(command.split(" ")[2]);
                break;
            default:
                break;
        }
    }
}
