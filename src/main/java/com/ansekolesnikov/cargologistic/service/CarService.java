package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.entity.PackModelEntity;
import com.ansekolesnikov.cargologistic.interfaces.ICarService;
import com.ansekolesnikov.cargologistic.repository.PackModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CarService implements ICarService {
    @Override
    public void loadPack(Car car, Pack pack) {
        if (car.getWidth() >= pack.getWidth()) {
            String stringLoadAddress = findLoadPackAddress(car, pack);
            if (!Objects.equals(stringLoadAddress.split(" ")[0], "not")) {
                loadPackOnCargoAddress(
                        car,
                        pack,
                        Integer.parseInt(stringLoadAddress.split(" ")[0]),
                        Integer.parseInt(stringLoadAddress.split(" ")[1])
                );
                pack.setCarId(car.getId());
            }
        }
    }

    @Override
    public int calcPercentLoad(Car car) {
        int countFilledPoints = 0;

        for (int i = 0; i < car.getHeight(); i++) {
            for (int j = 0; j < car.getWidth(); j++) {
                if (!Objects.equals(car.getCargo()[i][j], "0")) {
                    countFilledPoints++;
                }
            }
        }
        return (countFilledPoints * 100) / (car.getWidth() * car.getHeight());
    }

    @Override
    public String toStringCarCargoScheme(Car car) {
        String[][] cargo = car.getCargo();
        StringBuilder cargoInfo = new StringBuilder();

        for (int i = car.getHeight() - 1; i >= 0; i--) {
            cargoInfo.append("+");
            for (int j = 0; j < car.getWidth(); j++) {
                if (Objects.equals(cargo[i][j], "0")) {
                    cargoInfo.append(" ");
                } else {
                    cargoInfo.append(cargo[i][j]);
                }
            }
            cargoInfo.append("+\n");
        }
        cargoInfo.append("+".repeat(Math.max(0, car.getWidth() + 2))).append("\n");
        return cargoInfo.toString();
    }

    @Override
    public String toStringCarInfo(Car car, PackModelRepository packModelRepository) {
        StringBuilder fullInfoString = new StringBuilder(
                "Идентификатор: #" + car.getIdCar()
                        + "\nПараметры кузова: " + car.getWidth() + "х" + car.getHeight()
                        + "\nЗагруженность: " + calcPercentLoad(car) + "%"
                        + "\nСостав кузова:"
        );

        StringBuilder cargoString = new StringBuilder();
        for (int i = 0; i < car.getHeight(); i++) {
            for (int j = 0; j < car.getWidth(); j++) {
                cargoString.append(car.getCargo()[i][j]);
            }
        }

        for (Character code : Arrays.stream(cargoString.toString().split(""))
                .distinct()
                .map(c -> c.charAt(0))
                .filter(c -> c != '0')
                .toList()
        ) {
            int countPackages = calculateCountPackInCarByCode(car, code, packModelRepository);
            fullInfoString.append((countPackages != 0 ? "\n- посылка '" + code + "': " + countPackages + " шт." : ""));
        }

        fullInfoString.append("\nСхема кузова:\n").append(toStringCarCargoScheme(car)).append("\n\n");
        return fullInfoString.toString();
    }

    private int calculateCountPackInCarByCode(Car car, Character code, PackModelRepository packModelRepository) {
        PackModelEntity packModelEntity = packModelRepository.findByCode(code);
        int packSize = packModelEntity.getScheme().replaceAll("0", "").length();
        return Arrays.deepToString(car.getCargo()).replaceAll("[^" + code + "]", "").length() / packSize;
    }


    private String findLoadPackAddress(Car car, Pack pack) {
        for (int i = 0; i < car.getHeight(); i++) {
            for (int j = 0; j < car.getWidth(); j++) {
                if (isCanLoadPackOnCargoAddress(car, pack, i, j)
                        && Objects.equals(car.getCargo()[i][j], "0")) {
                    return i + " " + j;
                }
            }
        }
        return "not";
    }

    private void loadPackOnCargoAddress(Car car, Pack pack, int height, int width) {
        for (int i = 0; i < pack.getHeight(); i++) {
            for (int j = 0; j < pack.getWidth(); j++) {
                if (!Objects.equals(pack.getArrScheme()[i][j], "0")) {
                    car.getCargo()[i + height][j + width] = pack.getArrScheme()[i][j];
                }
            }
        }
    }

    private boolean isCanLoadPackOnCargoAddress(Car car, Pack pack, int height, int width) {
        for (int i = 0; i < pack.getHeight(); i++) {
            for (int j = 0; j < pack.getWidth(); j++) {
                if (i + height >= car.getHeight()
                        || j + width >= car.getWidth()) {
                    return false;
                }
                if (!Objects.equals(pack.getArrScheme()[i][j], "0")
                        && !Objects.equals(car.getCargo()[i + height][j + width], "0")) {
                    return false;
                }
            }
        }
        return true;
    }
}
