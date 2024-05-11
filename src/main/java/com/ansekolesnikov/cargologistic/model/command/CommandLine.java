package com.ansekolesnikov.cargologistic.model.command;

import com.ansekolesnikov.cargologistic.model.command.car.CarCommandLine;
import com.ansekolesnikov.cargologistic.model.command.pack.PackCommandLine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommandLine {
    private CarCommandLine carCommandLine;
    private PackCommandLine packCommandLine;
    public CommandLine(String command) {
        switch (command.split(" ")[0].toLowerCase()) {
            case "load":
                break;
            case "view":
                break;
            case "car":
                carCommandLine = new CarCommandLine(command);
                break;
            case "pack":
                packCommandLine = new PackCommandLine(command);
                break;
            default:
                break;
        }
    }
}
