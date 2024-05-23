package com.ansekolesnikov.cargologistic.validation.service;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.entity.command.car.CarCommandLine;
import lombok.Getter;

/**
 * Пока не используется
 */
@Getter
public class CarServiceValidation {
    private String errorMessage = "";
    public boolean isValidCommand (CommandLine commandLine) {
        CarCommandLine command = commandLine.getCarCommandLine();
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
