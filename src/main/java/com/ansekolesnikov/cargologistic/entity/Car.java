package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
public class Car extends CarModelEntity {
    private String[][] cargo;
    private int idCar;

    public Car(CarModelEntity carModelEntity) {
        this.id = carModelEntity.getId();
        this.name = carModelEntity.getName();
        this.width = carModelEntity.getWidth();
        this.height = carModelEntity.getHeight();
        this.cargo = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cargo[i][j] = "0";
            }
        }
    }

    public Car(JSONObject JSONObj) {
        this.idCar = Integer.parseInt(JSONObj.getString("id"));
        this.width = 6;
        this.height = 6;
        this.cargo = new String[height][width];
        initCargoFromString(JSONObj.getString("cargo"));
    }

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

    private void initCargoFromString(String schemeString) {
        int index = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cargo[i][j] = String.valueOf(schemeString.charAt(index++));
            }
        }
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

    public String toStringCarInfo(PackModelDao packModelDao) {
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
            int countPackages = calculateCountPackInCarByCode(code, packModelDao);
            fullInfoString.append((countPackages != 0 ? "\n- посылка '" + code + "': " + countPackages + " шт." : ""));
        }

        fullInfoString.append("\nСхема кузова:\n").append(toStringCarCargoScheme()).append("\n\n");
        return fullInfoString.toString();
    }

    public int calculateCountPackInCarByCode(Character code, PackModelDao packModelDao) {
        PackModelEntity packModelEntity = PackModelEntity.to(packModelDao.findByCode(code));
        int packSize = packModelEntity.getScheme().replaceAll("0", "").length();
        return Arrays.deepToString(cargo).replaceAll("[^" + code + "]", "").length() / packSize;
    }
}
