package com.ansekolesnikov.cargologistic.model.command.load_list;

import com.ansekolesnikov.cargologistic.model.pack.Pack;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class LoadListCommandLine {
    private String carModel;
    private String algorithm;
    private int countCars;
    private String[] packs;

    public LoadListCommandLine(String command) {
        System.out.println(command);
        this.carModel = command.split(" ")[1];
        this.algorithm = command.split(" ")[2].toLowerCase();
        this.countCars = Integer.parseInt(
                command.split(" ")[3]
                        .replaceAll("[^0-9]", "")
        );
        this.packs = command
                .substring(command.indexOf(":") + 1)
                .trim()
                .split("\\n");
    }

}
