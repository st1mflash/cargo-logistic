package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.repository.PackModelRepository;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Getter
@Setter
public class Car extends CarModelEntity {
    private String[][] cargo;
    private int idCar;

    public void loadPack(Pack pack) {
        if (width >= pack.getWidth()) {
            String stringLoadAddress = findLoadPackAddress(pack);
            if (!Objects.equals(stringLoadAddress.split(" ")[0], "not")) {
                loadPackOnCargoAddress(
                        pack,
                        Integer.parseInt(stringLoadAddress.split(" ")[0]),
                        Integer.parseInt(stringLoadAddress.split(" ")[1])
                );
                pack.setCarId(id);
            }
        }
    }

    public int calcPercentLoad() {
        int countFilledPoints = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!Objects.equals(cargo[i][j], "0")) {
                    countFilledPoints++;
                }
            }
        }
        return (countFilledPoints * 100) / (width * height);
    }

    public String toStringCarCargoScheme() {
        String[][] cargo = getCargo();
        StringBuilder cargoInfo = new StringBuilder();

        for (int i = height - 1; i >= 0; i--) {
            cargoInfo.append("+");
            for (int j = 0; j < width; j++) {
                if (Objects.equals(cargo[i][j], "0")) {
                    cargoInfo.append(" ");
                } else {
                    cargoInfo.append(cargo[i][j]);
                }
            }
            cargoInfo.append("+\n");
        }
        cargoInfo.append("+".repeat(Math.max(0, width + 2))).append("\n");
        return cargoInfo.toString();
    }

    private String findLoadPackAddress(Pack pack) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isCanLoadPackOnCargoAddress(pack, i, j)
                        && Objects.equals(cargo[i][j], "0")) {
                    return i + " " + j;
                }
            }
        }
        return "not";
    }

    private void loadPackOnCargoAddress(Pack pack, int height, int width) {
        for (int i = 0; i < pack.getHeight(); i++) {
            for (int j = 0; j < pack.getWidth(); j++) {
                if (!Objects.equals(pack.getArrScheme()[i][j], "0")) {
                    cargo[i + height][j + width] = pack.getArrScheme()[i][j];
                }
            }
        }
    }

    private boolean isCanLoadPackOnCargoAddress(Pack pack, int height, int width) {
        for (int i = 0; i < pack.getHeight(); i++) {
            for (int j = 0; j < pack.getWidth(); j++) {
                if (i + height >= this.height
                        || j + width >= this.width) {
                    return false;
                }
                if (!Objects.equals(pack.getArrScheme()[i][j], "0")
                        && !Objects.equals(cargo[i + height][j + width], "0")) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toStringCarInfo(PackModelRepository packModelRepository) {
        StringBuilder fullInfoString = new StringBuilder(
                "Идентификатор: #" + idCar
                        + "\nПараметры кузова: " + width + "х" + height
                        + "\nЗагруженность: " + calcPercentLoad() + "%"
                        + "\nСостав кузова:"
        );

        StringBuilder cargoString = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cargoString.append(cargo[i][j]);
            }
        }

        for (Character code : Arrays.stream(cargoString.toString().split(""))
                .distinct()
                .map(c -> c.charAt(0))
                .filter(c -> c != '0')
                .toList()
        ) {
            int countPackages = calculateCountPackInCarByCode(code, packModelRepository);
            fullInfoString.append((countPackages != 0 ? "\n- посылка '" + code + "': " + countPackages + " шт." : ""));
        }

        fullInfoString.append("\nСхема кузова:\n").append(toStringCarCargoScheme()).append("\n\n");
        return fullInfoString.toString();
    }

    public int calculateCountPackInCarByCode(Character code, PackModelRepository packModelRepository) {
        PackModelEntity packModelEntity = packModelRepository.findByCode(code);
        int packSize = packModelEntity.getScheme().replaceAll("0", "").length();
        return Arrays.deepToString(cargo).replaceAll("[^" + code + "]", "").length() / packSize;
    }
}
