package com.ansekolesnikov.cargologistic.model.command.view_file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class ViewFileCommandLine {
    private String fileName;

    public ViewFileCommandLine(String command) {
        this.fileName = command.split(" ")[1];
    }
}
