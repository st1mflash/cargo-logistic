package com.ansekolesnikov.cargologistic.model;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class Cargo {
    private int id = new Random().nextInt(1000000);
    public static final int WIDTH = 6;  //  ширина кузова
    public static final int HEIGHT = 6; //  высота кузова
    private final int[][] arrCargoScheme = new int[HEIGHT][WIDTH];          //  массив содержимого кузова
    private int loadPercent = 0;            //  процент загрузки
    private static final Logger logger = Logger.getLogger(Cargo.class);

    public Cargo() {
    }

    public static List<Cargo> loadListCargo(List<Package> listPackages, String algorithm, int countCars) {
        List<Cargo> listCargo = new ArrayList<>();
        do {
            Cargo cargo = new Cargo();
            listCargo.add(cargo);
            listPackages = listPackages.stream()
                    .filter(pack -> pack.getIdCargo() == 0)
                    .collect(Collectors.toList());

            for (Package pack : listPackages) {
                Algorithm.load(algorithm, cargo, pack);
            }
            if (countCars > 0) {
                if (cargo.getLoadPercent() == 0) {
                    logger.info("Грузовик #" + cargo.getId() + " остался пустым");
                } else {
                    logger.info("Грузовик #" + cargo.getId() + " успешно загружен на " + cargo.getLoadPercent() + "%");
                }
            }

            countCars--;
        } while (
                listPackages
                        .stream()
                        .anyMatch(pack -> pack.getIdCargo() == 0)
                        || countCars > 0
        );
        return listCargo;
    }

    public static void printListCargo(List<Cargo> listCargo) {
        if (listCargo != null) {
            for (Cargo cargo : listCargo) {
                cargo.printCargo();
            }
        }
    }

    public void printCargoFullInfo() {
        String fullInfo;
        fullInfo = "Идентификатор: #" + id
                + "\nПараметры кузова: " + Cargo.WIDTH + "х" + Cargo.HEIGHT
                + "\nЗагруженность: " + getLoadPercent() + "%"
                + "\nСостав груза:"
                + (countTypeLoad(1) != 0 ? "\n - тип '1': " + countTypeLoad(1) + " шт." : "")
                + (countTypeLoad(2) != 0 ? "\n - тип '2': " + countTypeLoad(2) + " шт." : "")
                + (countTypeLoad(3) != 0 ? "\n - тип '3': " + countTypeLoad(3) + " шт." : "")
                + (countTypeLoad(4) != 0 ? "\n - тип '4': " + countTypeLoad(4) + " шт." : "")
                + (countTypeLoad(5) != 0 ? "\n - тип '5': " + countTypeLoad(5) + " шт." : "")
                + (countTypeLoad(6) != 0 ? "\n - тип '6': " + countTypeLoad(6) + " шт." : "")
                + (countTypeLoad(7) != 0 ? "\n - тип '7': " + countTypeLoad(7) + " шт." : "")
                + (countTypeLoad(8) != 0 ? "\n - тип '8': " + countTypeLoad(8) + " шт." : "")
                + (countTypeLoad(9) != 0 ? "\n - тип '9': " + countTypeLoad(9) + " шт." : "")
                + "\nСхема кузова:\n"
                + getStringCargoScheme()
                + "\n";
        System.out.println(fullInfo);
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
        mapJsonCargo.put("width", String.valueOf(Cargo.WIDTH));
        mapJsonCargo.put("height", String.valueOf(Cargo.HEIGHT));
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

    public static Cargo exportCargoFromJSON(JSONObject jsonCargo) {
        Cargo cargo = new Cargo();
        cargo.setId(Integer.parseInt(jsonCargo.getString("id")));
        cargo.initCargoSchemeFromString(jsonCargo.getString("cargo"));
        return cargo;
    }

    public void initCargoSchemeFromString(String schemeString) {
        int index = 0;
        for (int i = 0; i < Cargo.HEIGHT; i++) {
            for (int j = 0; j < Cargo.WIDTH; j++) {
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
