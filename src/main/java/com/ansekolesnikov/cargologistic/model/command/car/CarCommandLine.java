package com.ansekolesnikov.cargologistic.model.command.car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Getter
@Setter
@Component
public class CarCommandLine {
    private String operation;
    private String nameCar;
    private int widthSchemeCargoCar;
    private int heightSchemeCargoCar;
    private int idCar;
    private String paramName;
    private String paramValue;
    private String text;
    public CarCommandLine(String command) {
        this.text = command;
        this.operation = command.split(" ")[1].toLowerCase();
        switch (this.operation) {
            case "insert":
                this.nameCar = command.split(" ")[2];
                this.widthSchemeCargoCar = Integer.parseInt(command.split(" ")[3]);
                this.heightSchemeCargoCar = Integer.parseInt(command.split(" ")[4]);
                break;
            case "update":
                this.idCar = Integer.parseInt(command.split(" ")[2]);
                this.paramName = command.split(" ")[3].toLowerCase();
                this.paramValue = command.split(" ")[4];
                break;
            case "delete":
                this.idCar = Integer.parseInt(command.split(" ")[2]);
                break;
            default:
                break;
        }
    }
}
