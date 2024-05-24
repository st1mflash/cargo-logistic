package com.ansekolesnikov.cargologistic.service.service_input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class ViewFileServiceInput extends ServiceInput {
    private String fileName;

    public ViewFileServiceInput(String command) {
        this.fileName = command.split(" ")[1];
    }
}
