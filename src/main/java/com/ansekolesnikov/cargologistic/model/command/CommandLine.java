package com.ansekolesnikov.cargologistic.model.command;

import com.ansekolesnikov.cargologistic.model.command.car.CarCommandLine;
import com.ansekolesnikov.cargologistic.model.command.load_file.LoadFileCommandLine;
import com.ansekolesnikov.cargologistic.model.command.pack.PackCommandLine;
import com.ansekolesnikov.cargologistic.model.command.view_file.ViewFileCommandLine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommandLine {
    private LoadFileCommandLine loadFileCommandLine;
    private ViewFileCommandLine viewFileCommandLine;
    private CarCommandLine carCommandLine;
    private PackCommandLine packCommandLine;

    public CommandLine(String command) {
        switch (command.split(" ")[0].toLowerCase()) {
            case "load_file":
                loadFileCommandLine = new LoadFileCommandLine(command);
                break;
            case "view_file":
                viewFileCommandLine = new ViewFileCommandLine(command);
                break;
            case "load":
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
