package com.ansekolesnikov.cargologistic.model.command.load;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class LoadFileCommandLine {
    private String fileName;
    private String algorithm;
    private int countCars;

    public LoadFileCommandLine(String command) {
        this.fileName = command.split(" ")[1];
        this.algorithm = command.split(" ")[2].toLowerCase();
        this.countCars = Integer.parseInt(command.split(" ")[3]);
    }
}
