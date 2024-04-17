package com.ansekolesnikov.cargologistic.model;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class CargoCar {
    private int id = new Random().nextInt(1000000);
    public static final int WIDTH = 6;  //  ширина кузова
    public static final int HEIGHT = 6; //  высота кузова
    private final int[][] arrCargoScheme = new int[HEIGHT][WIDTH];          //  массив содержимого кузова
    private int loadPercent = 0;            //  процент загрузки
    private static final Logger LOGGER = Logger.getLogger(CargoCar.class);

    public CargoCar() {
    }

    public static List<CargoCar> loadListCargo(List<CargoPackage> listCargoPackages, String algorithm, int countCars) {
        List<CargoCar> listCargoCars = new ArrayList<>();
        do {
            CargoCar cargoCar = new CargoCar();
            listCargoCars.add(cargoCar);
            listCargoPackages = listCargoPackages.stream()
                    .filter(pack -> pack.getIdCargo() == 0)
                    .collect(Collectors.toList());

            for (CargoPackage pack : listCargoPackages) {
                CargoLoadAlgorithm.load(algorithm, cargoCar, pack);
            }
            if (countCars > 0) {
                if (cargoCar.getLoadPercent() == 0) {
                    LOGGER.info("Грузовик #" + cargoCar.getId() + " остался пустым");
                } else {
                    LOGGER.info("Грузовик #" + cargoCar.getId() + " успешно загружен на " + cargoCar.getLoadPercent() + "%");
                }
            }

            countCars--;
        } while (
                listCargoPackages
                        .stream()
                        .anyMatch(pack -> pack.getIdCargo() == 0)
                        || countCars > 0
        );
        return listCargoCars;
    }

    public static void printListCargo(List<CargoCar> listCargoCars) {
        if (listCargoCars != null) {
            for (CargoCar cargoCar : listCargoCars) {
                cargoCar.printCargo();
            }
        }
    }

    public String getCargoCarFullInfo() {
        StringBuilder fullInfo = new StringBuilder(
                "Идентификатор: #" + id
                        + "\nПараметры кузова: " + CargoCar.WIDTH + "х" + CargoCar.HEIGHT
                        + "\nЗагруженность: " + getLoadPercent() + "%"
                        + "\nСостав груза:"
        );
        for (int i = 1; i < 10; i++) {
            fullInfo.append((countTypeLoad(i) != 0 ? "\n - тип '" + i + "': " + countTypeLoad(i) + " шт." : ""));
        }
        fullInfo.append("\nСхема кузова:\n").append(getStringCargoScheme()).append("\n\n");
        return fullInfo.toString();
    }

    private int countTypeLoad(int type) {
        String loadToString = Arrays.deepToString(arrCargoScheme).replaceAll("\\D", "");
        return (loadToString.length() - (loadToString.replace(Integer.toString(type), "").length())) / type;
    }

    public void printCargo() {
        String line;
        for (int i = HEIGHT - 1; i >= 0; i--) {
            line = "+";
            for (int j = 0; j < WIDTH; j++) {
                if (arrCargoScheme[i][j] == 0) {
                    line = line + " ";
                } else {
                    line = line + arrCargoScheme[i][j];
                }
            }
            line = line + "+";
            System.out.println(line);
        }
        System.out.println("++++++++\n");
    }

    public String getJsonString() {
        StringBuilder cargoSchemeToString = new StringBuilder();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                cargoSchemeToString.append(arrCargoScheme[i][j]);
            }
        }
        Map<String, String> mapJsonCargo = new HashMap<>();
        mapJsonCargo.put("id", String.valueOf(this.id));
        mapJsonCargo.put("width", String.valueOf(CargoCar.WIDTH));
        mapJsonCargo.put("height", String.valueOf(CargoCar.HEIGHT));
        mapJsonCargo.put("cargo", cargoSchemeToString.toString());
        return String.valueOf(new JSONObject(mapJsonCargo));
    }

    public boolean checkPackageSupport(int coordinateHeight, int coordinateWidth, int packageWidth) {
        if (coordinateHeight == 0) {
            return true;
        } else {
            int sumPackageSupport = 0;
            for (int j = coordinateWidth; j < coordinateWidth + packageWidth; j++) {
                if (arrCargoScheme[coordinateHeight - 1][j] != 0) {
                    sumPackageSupport = sumPackageSupport + 1;
                }
            }
            return sumPackageSupport > packageWidth / 2;
        }
    }

    public int getLoadPercent() {
        int fillPoints = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (arrCargoScheme[i][j] != 0) {
                    fillPoints++;
                }
            }
        }
        return loadPercent = (fillPoints * 100) / (WIDTH * HEIGHT);
    }

    public int getId() {
        return id;
    }

    public int[][] getArrCargoScheme() {
        return arrCargoScheme;
    }

    public static CargoCar exportCargoFromJSON(JSONObject jsonCargo) {
        CargoCar cargoCar = new CargoCar();
        cargoCar.setId(Integer.parseInt(jsonCargo.getString("id")));
        cargoCar.initCargoSchemeFromString(jsonCargo.getString("cargo"));
        return cargoCar;
    }

    public void initCargoSchemeFromString(String schemeString) {
        int index = 0;
        for (int i = 0; i < CargoCar.HEIGHT; i++) {
            for (int j = 0; j < CargoCar.WIDTH; j++) {
                arrCargoScheme[i][j] = Integer.parseInt(String.valueOf(schemeString.charAt(index++)));
            }
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStringCargoScheme() {
        String scheme = "";
        for (int i = HEIGHT - 1; i >= 0; i--) {
            scheme = scheme + "\t\t+";
            for (int j = 0; j < WIDTH; j++) {
                if (arrCargoScheme[i][j] == 0) {
                    scheme = scheme + " ";
                } else {
                    scheme = scheme + arrCargoScheme[i][j];
                }
            }
            scheme = scheme + "+\n";
        }
        scheme = scheme + "\t\t++++++++";
        return scheme;
    }
}
