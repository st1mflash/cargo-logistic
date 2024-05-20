package com.ansekolesnikov.cargologistic.model.command.load_file;

import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
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
    private AlgorithmEnum algorithm;
    private int countCars;

    public LoadFileCommandLine(String command) {
        this.fileName = command.split(" ")[1];
        this.algorithm = AlgorithmEnum.initEnumFromString(command.split(" ")[2]);
        this.countCars = Integer.parseInt(command.split(" ")[3]);
    }
}
