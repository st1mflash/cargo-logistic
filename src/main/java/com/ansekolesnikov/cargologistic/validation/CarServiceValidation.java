package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceRequest;
import com.ansekolesnikov.cargologistic.service.service_input.CarModelServiceRequest;
import lombok.Getter;

/**
 * Пока не используется
 */
@Getter
public class CarServiceValidation {
    private String errorMessage = "";
    public boolean isValidCommand (ServiceRequest serviceRequest) {
        CarModelServiceRequest command = serviceRequest.getCarModelServiceInput();
        switch (command.getOperation()) {
            case INSERT:

                break;
            default:
                errorMessage = "Не удалось определить введенную операцию (доступные операции: INSERT/UPDATE/DELETE/LIST" +
                        " - в любом регистре)";
                return false;
        }
        return true;
    }
}
