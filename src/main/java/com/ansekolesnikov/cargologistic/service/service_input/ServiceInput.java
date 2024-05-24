package com.ansekolesnikov.cargologistic.service.service_input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ServiceInput {
    private LoadFileServiceInput loadFileServiceInput;
    private LoadListServiceInput loadListServiceInput;
    private ViewFileServiceInput viewFileServiceInput;
    private CarModelServiceInput carModelServiceInput;
    private PackModelServiceInput packModelServiceInput;

    public ServiceInput(String command) {
        switch (command.split(" ")[0].toLowerCase()) {
            case "load_file":
                loadFileServiceInput = new LoadFileServiceInput(command);
                break;
            case "load_list":
                loadListServiceInput = new LoadListServiceInput(command);
                break;
            case "view_file":
                viewFileServiceInput = new ViewFileServiceInput(command);
                break;
            case "car":
                carModelServiceInput = new CarModelServiceInput(command);
                break;
            case "pack":
                packModelServiceInput = new PackModelServiceInput(command);
                break;
            default:
                break;
        }
    }
}
