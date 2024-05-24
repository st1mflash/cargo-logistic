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
public class LoadListServiceInput extends ServiceInput {
    private String carModel;
    private AlgorithmEnum algorithm;
    private int countCars;
    private String[] packs;

    public LoadListServiceInput(String command) {
        try {
            this.carModel = command.split(" ")[1];
            this.algorithm = AlgorithmEnum.initEnumFromString(command.split(" ")[2]);
            this.countCars = Integer.parseInt(
                    command.split(" ")[3].split("[^0-9]")[0]
                            .replaceAll("[^0-9]", "")
            );
            this.packs = command
                    .substring(command.indexOf(":") + 1)
                    .trim()
                    .split("\\n");
        } catch (RuntimeException ignored) {}
    }

}
