package com.ansekolesnikov.cargologistic.validation;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_input.CarModelServiceInput;
import lombok.Getter;

/**
 * Пока не используется
 */
@Getter
public class CarServiceValidation {
    private String errorMessage = "";
    public boolean isValidCommand (ServiceInput serviceInput) {
        CarModelServiceInput command = serviceInput.getCarModelServiceInput();
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
