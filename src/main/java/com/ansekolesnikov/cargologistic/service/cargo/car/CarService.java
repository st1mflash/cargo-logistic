package com.ansekolesnikov.cargologistic.service.cargo.car;

import com.ansekolesnikov.cargologistic.model.car.Car;
import com.ansekolesnikov.cargologistic.model.car.CarToStringUtils;
import com.ansekolesnikov.cargologistic.model.car.CarUtils;
import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.car.CarCommandLine;
import com.ansekolesnikov.cargologistic.model.command.pack.PackCommandLine;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
@Getter
@Setter
public class CarService implements CargoService {
    private DatabaseService databaseService;
    private CarCommandLine carCommandLine;
    private CarServiceUtils carServiceUtils = new CarServiceUtils();
    private CarToStringUtils carToStringUtils = new CarToStringUtils();
    private CarUtils carUtils = new CarUtils();

    public CarService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public String runService(String params) {
        return "";
    }

    @Override
    public String runService(CommandLine command) {
        carCommandLine = command.getCarCommandLine();
        return switch (carCommandLine.getOperation()) {
            case "insert" -> insertCarIntoDatabase(
                    carServiceUtils.createPackFromCommand(carCommandLine)
            );
            case "update" -> updateCarInDatabase(
                    findCarByIdInDatabase(
                            carCommandLine.getIdCar()
                    ),
                    carCommandLine
            );
            case "delete" -> deleteCarFromDatabase(
                    findCarByIdInDatabase(
                            carCommandLine.getIdCar()
                    )
            );
            default -> null;
        };
    }

    private Car findCarByIdInDatabase(int id) {
        return databaseService
                .getOperationsDatabase()
                .getCarOperations()
                .queryById(id)
                ;
    }

    private String insertCarIntoDatabase(Car car) {
        databaseService.getOperationsDatabase().getCarOperations().insert(car);
        return "Грузовик '" + car.getName() + "' успешно создан.\n\n"
                + carToStringUtils.toStringCarInfo(car);
    }

    private String updateCarInDatabase(Car car, CarCommandLine command) {
        Car updatedCar = car;
        switch (command.getParamName()) {
            case "name":
                updatedCar.setName(command.getParamValue());
                break;
            case "cargo_width":
                updatedCar.setWidth(Integer.parseInt(command.getParamValue()));
                break;
            case "cargo_height":
                updatedCar.setHeight(Integer.parseInt(command.getParamValue()));
                break;
            default:
                break;
        }
        databaseService.getOperationsDatabase().getCarOperations().update(updatedCar);
        return "Грузовик '" + car.getName() + "' успешно обновлен.\n\n"
                + carToStringUtils.toStringCarInfo(car);
    }

    private String deleteCarFromDatabase(Car car) {
        databaseService.getOperationsDatabase().getCarOperations().delete(car);
        return "Грузовик '" + car.getName() + "' успешно удален.";
    }
}
