package com.ansekolesnikov.cargologistic.service.service_input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Setter
@Component
public class ViewFileServiceRequest extends ServiceRequest {
    private String fileName;

    public ViewFileServiceRequest(String command) {
        this.fileName = command.split(" ")[1];
    }
}
