package com.ansekolesnikov.cargologistic.model;

public class CargoLoadAlgorithm {
    public static void load(String algorithm, CargoCar cargoCar, CargoPackage pack) {
        switch (algorithm) {
            case "max":
                loadAlgorithm(cargoCar, pack);
                break;
            case "half":
                loadHalfAlgorithm(cargoCar, pack);
                break;
            case "type":
                loadTypeAlgorithm(cargoCar, pack);
                break;
            default:
                break;
        }
    }

    private static void loadAlgorithm(CargoCar cargoCar, CargoPackage pack) {
        int[][] arrCargoScheme = cargoCar.getArrCargoScheme();
        switch (pack.getType()) {
            case 1:
                for (int i = 0; i < CargoCar.WIDTH; i++) {
                    for (int j = 0; j < CargoCar.HEIGHT; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && cargoCar.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            pack.setIdCargo(cargoCar.getId());

                            return;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < CargoCar.HEIGHT; i++) {
                    for (int j = 0; j < CargoCar.WIDTH - 1; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && cargoCar.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            pack.setIdCargo(cargoCar.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < CargoCar.HEIGHT; i++) {
                    for (int j = 0; j < CargoCar.WIDTH - 2; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && cargoCar.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            pack.setIdCargo(cargoCar.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 4:
                for (int i = 0; i < CargoCar.HEIGHT; i++) {
                    for (int j = 0; j < CargoCar.WIDTH - 3; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i][j + 3] == 0
                                && cargoCar.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            arrCargoScheme[i][j + 3] = pack.getType();
                            pack.setIdCargo(cargoCar.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 5:
                for (int i = 0; i < CargoCar.HEIGHT; i++) {
                    for (int j = 0; j < CargoCar.WIDTH - 4; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i][j + 3] == 0
                                && arrCargoScheme[i][j + 4] == 0
                                && cargoCar.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            arrCargoScheme[i][j + 3] = pack.getType();
                            arrCargoScheme[i][j + 4] = pack.getType();
                            pack.setIdCargo(cargoCar.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 6:
                for (int i = 0; i < CargoCar.HEIGHT - 1; i++) {
                    for (int j = 0; j < CargoCar.WIDTH - 2; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i + 1][j] == 0
                                && arrCargoScheme[i + 1][j + 1] == 0
                                && arrCargoScheme[i + 1][j + 2] == 0
                                && cargoCar.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            arrCargoScheme[i + 1][j] = pack.getType();
                            arrCargoScheme[i + 1][j + 1] = pack.getType();
                            arrCargoScheme[i + 1][j + 2] = pack.getType();
                            pack.setIdCargo(cargoCar.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 7:
                for (int i = 0; i < CargoCar.HEIGHT - 1; i++) {
                    for (int j = 0; j < CargoCar.WIDTH - 3; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i][j + 3] == 0
                                && arrCargoScheme[i + 1][j] == 0
                                && arrCargoScheme[i + 1][j + 1] == 0
                                && arrCargoScheme[i + 1][j + 2] == 0
                                && cargoCar.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            arrCargoScheme[i][j + 3] = pack.getType();
                            arrCargoScheme[i + 1][j] = pack.getType();
                            arrCargoScheme[i + 1][j + 1] = pack.getType();
                            arrCargoScheme[i + 1][j + 2] = pack.getType();
                            pack.setIdCargo(cargoCar.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 8:
                for (int i = 0; i < CargoCar.HEIGHT - 1; i++) {
                    for (int j = 0; j < CargoCar.WIDTH - 3; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i][j + 3] == 0
                                && arrCargoScheme[i + 1][j] == 0
                                && arrCargoScheme[i + 1][j + 1] == 0
                                && arrCargoScheme[i + 1][j + 2] == 0
                                && arrCargoScheme[i + 1][j + 3] == 0
                                && cargoCar.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            arrCargoScheme[i][j + 3] = pack.getType();
                            arrCargoScheme[i + 1][j] = pack.getType();
                            arrCargoScheme[i + 1][j + 1] = pack.getType();
                            arrCargoScheme[i + 1][j + 2] = pack.getType();
                            arrCargoScheme[i + 1][j + 3] = pack.getType();
                            pack.setIdCargo(cargoCar.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 9:
                for (int i = 0; i < CargoCar.HEIGHT - 2; i++) {
                    for (int j = 0; j < CargoCar.WIDTH - 2; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i + 1][j] == 0
                                && arrCargoScheme[i + 1][j + 1] == 0
                                && arrCargoScheme[i + 1][j + 2] == 0
                                && arrCargoScheme[i + 2][j] == 0
                                && arrCargoScheme[i + 2][j + 1] == 0
                                && arrCargoScheme[i + 2][j + 2] == 0
                                && cargoCar.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            arrCargoScheme[i + 1][j] = pack.getType();
                            arrCargoScheme[i + 1][j + 1] = pack.getType();
                            arrCargoScheme[i + 1][j + 2] = pack.getType();
                            arrCargoScheme[i + 2][j] = pack.getType();
                            arrCargoScheme[i + 2][j + 1] = pack.getType();
                            arrCargoScheme[i + 2][j + 2] = pack.getType();
                            pack.setIdCargo(cargoCar.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;

        }
    }

    private static void loadHalfAlgorithm(CargoCar cargoCar, CargoPackage pack) {
        if (cargoCar.getLoadPercent() + (pack.getType() * 100) / (CargoCar.WIDTH * CargoCar.HEIGHT) <= 50) {
            loadAlgorithm(cargoCar, pack);
        }
    }

    private static void loadTypeAlgorithm(CargoCar cargoCar, CargoPackage pack) {
        if (cargoCar.getArrCargoScheme()[0][0] == pack.getType()
                || cargoCar.getArrCargoScheme()[0][0] == 0) {
            loadAlgorithm(cargoCar, pack);
        }
    }

    public static void checkAlgorithm(String algorithm) {
        /*
        Logger logger = Logger.getLogger(Algorithm.class.getName());
        switch (algorithm) {
            case "max":
                logger.info("Начало загрузки карго из файла '" + fileName  + "'. Алгоритм загрузки: каждый грузовик загружается максимально ('max')");
                break;
            case "half":
                logger.info("Начало загрузки карго из файла '" + fileName  + "'. Алгоритм загрузки: каждый грузовик загружается не более, чем на половину ('half')");
                break;
            case "type":
                logger.info("Начало загрузки карго из файла '" + fileName  + "'. Алгоритм загрузки: каждый грузовик загружается посылками одного типа ('type')");
                break;
            default:
                logger.error("Ошибка ввода. Не удалось определить алгоритм '" + algorithm +"'");
                return """
                        
                        Не удалось определить алгоритм загрузки.
                        Доступные алгоритмы:
                        \tmax \t- максимально загружать каждый грузовик
                        \thalf \t- каждый грузовик загружается не более, чем на половину
                        \ttype \t- каждый грузовик загружается посылками одного типа
                        """;
        }

         */
    }
}
