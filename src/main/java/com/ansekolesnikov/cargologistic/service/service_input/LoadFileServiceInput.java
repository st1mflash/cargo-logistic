package com.ansekolesnikov.cargologistic.service.service_input;

import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class LoadFileServiceInput {
    private String fileName;
    private AlgorithmEnum algorithm;
    private int countCars;

    public LoadFileServiceInput(String command) {
        try {
            this.fileName = command.split(" ")[1];
            this.algorithm = AlgorithmEnum.initEnumFromString(command.split(" ")[2]);
            this.countCars = Integer.parseInt(command.split(" ")[3]);
        } catch (RuntimeException ignored) {}
    }
}
