package com.ansekolesnikov.cargologistic.service.service_input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ServiceRequest {
    private LoadFileServiceRequest loadFileServiceInput;
    private LoadListServiceRequest loadListServiceInput;
    private ViewFileServiceRequest viewFileServiceInput;
    private CarModelServiceRequest carModelServiceInput;
    private PackModelServiceRequest packModelServiceInput;

    public ServiceRequest(String command) {
        switch (command.split(" ")[0].toLowerCase()) {
            case "load_file":
                loadFileServiceInput = new LoadFileServiceRequest(command);
                break;
            case "load_list":
                loadListServiceInput = new LoadListServiceRequest(command);
                break;
            case "view_file":
                viewFileServiceInput = new ViewFileServiceRequest(command);
                break;
            case "car":
                carModelServiceInput = new CarModelServiceRequest(command);
                break;
            case "pack":
                packModelServiceInput = new PackModelServiceRequest(command);
                break;
            default:
                break;
        }
    }
}
