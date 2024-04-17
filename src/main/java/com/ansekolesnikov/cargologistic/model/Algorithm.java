package com.ansekolesnikov.cargologistic.model;

public class Algorithm {
    public static void load(String algorithm, Cargo cargo, Package pack) {
        switch (algorithm) {
            case "max":
                loadAlgorithm(cargo, pack);
                break;
            case "half":
                loadHalfAlgorithm(cargo, pack);
                break;
            case "type":
                loadTypeAlgorithm(cargo, pack);
                break;
            default:
                break;
        }
    }

    private static void loadAlgorithm(Cargo cargo, Package pack) {
        int[][] arrCargoScheme = cargo.getArrCargoScheme();
        switch (pack.getType()) {
            case 1:
                for (int i = 0; i < Cargo.WIDTH; i++) {
                    for (int j = 0; j < Cargo.HEIGHT; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && cargo.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            pack.setIdCargo(cargo.getId());

                            return;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < Cargo.HEIGHT; i++) {
                    for (int j = 0; j < Cargo.WIDTH - 1; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && cargo.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            pack.setIdCargo(cargo.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < Cargo.HEIGHT; i++) {
                    for (int j = 0; j < Cargo.WIDTH - 2; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && cargo.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            pack.setIdCargo(cargo.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 4:
                for (int i = 0; i < Cargo.HEIGHT; i++) {
                    for (int j = 0; j < Cargo.WIDTH - 3; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i][j + 3] == 0
                                && cargo.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            arrCargoScheme[i][j + 3] = pack.getType();
                            pack.setIdCargo(cargo.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 5:
                for (int i = 0; i < Cargo.HEIGHT; i++) {
                    for (int j = 0; j < Cargo.WIDTH - 4; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i][j + 3] == 0
                                && arrCargoScheme[i][j + 4] == 0
                                && cargo.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            arrCargoScheme[i][j + 3] = pack.getType();
                            arrCargoScheme[i][j + 4] = pack.getType();
                            pack.setIdCargo(cargo.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 6:
                for (int i = 0; i < Cargo.HEIGHT - 1; i++) {
                    for (int j = 0; j < Cargo.WIDTH - 2; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i + 1][j] == 0
                                && arrCargoScheme[i + 1][j + 1] == 0
                                && arrCargoScheme[i + 1][j + 2] == 0
                                && cargo.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            arrCargoScheme[i + 1][j] = pack.getType();
                            arrCargoScheme[i + 1][j + 1] = pack.getType();
                            arrCargoScheme[i + 1][j + 2] = pack.getType();
                            pack.setIdCargo(cargo.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 7:
                for (int i = 0; i < Cargo.HEIGHT - 1; i++) {
                    for (int j = 0; j < Cargo.WIDTH - 3; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i][j + 3] == 0
                                && arrCargoScheme[i + 1][j] == 0
                                && arrCargoScheme[i + 1][j + 1] == 0
                                && arrCargoScheme[i + 1][j + 2] == 0
                                && cargo.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            arrCargoScheme[i][j + 3] = pack.getType();
                            arrCargoScheme[i + 1][j] = pack.getType();
                            arrCargoScheme[i + 1][j + 1] = pack.getType();
                            arrCargoScheme[i + 1][j + 2] = pack.getType();
                            pack.setIdCargo(cargo.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 8:
                for (int i = 0; i < Cargo.HEIGHT - 1; i++) {
                    for (int j = 0; j < Cargo.WIDTH - 3; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i][j + 3] == 0
                                && arrCargoScheme[i + 1][j] == 0
                                && arrCargoScheme[i + 1][j + 1] == 0
                                && arrCargoScheme[i + 1][j + 2] == 0
                                && arrCargoScheme[i + 1][j + 3] == 0
                                && cargo.checkPackageSupport(i, j, pack.getWidth())
                        ) {
                            arrCargoScheme[i][j] = pack.getType();
                            arrCargoScheme[i][j + 1] = pack.getType();
                            arrCargoScheme[i][j + 2] = pack.getType();
                            arrCargoScheme[i][j + 3] = pack.getType();
                            arrCargoScheme[i + 1][j] = pack.getType();
                            arrCargoScheme[i + 1][j + 1] = pack.getType();
                            arrCargoScheme[i + 1][j + 2] = pack.getType();
                            arrCargoScheme[i + 1][j + 3] = pack.getType();
                            pack.setIdCargo(cargo.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;
            case 9:
                for (int i = 0; i < Cargo.HEIGHT - 2; i++) {
                    for (int j = 0; j < Cargo.WIDTH - 2; j++) {
                        if (arrCargoScheme[i][j] == 0
                                && arrCargoScheme[i][j + 1] == 0
                                && arrCargoScheme[i][j + 2] == 0
                                && arrCargoScheme[i + 1][j] == 0
                                && arrCargoScheme[i + 1][j + 1] == 0
                                && arrCargoScheme[i + 1][j + 2] == 0
                                && arrCargoScheme[i + 2][j] == 0
                                && arrCargoScheme[i + 2][j + 1] == 0
                                && arrCargoScheme[i + 2][j + 2] == 0
                                && cargo.checkPackageSupport(i, j, pack.getWidth())
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
                            pack.setIdCargo(cargo.getId());

                            //Log.logDebug(cargo, "Добавление груза #" + pack.getId() + " с типом '" + pack.getType() + "'");
                            return;
                        }
                    }
                }
                break;

        }
    }

    private static void loadHalfAlgorithm(Cargo cargo, Package pack) {
        if (cargo.getLoadPercent() + (pack.getType() * 100) / (Cargo.WIDTH * Cargo.HEIGHT) <= 50) {
            loadAlgorithm(cargo, pack);
        }
    }

    private static void loadTypeAlgorithm(Cargo cargo, Package pack) {
        if (cargo.getArrCargoScheme()[0][0] == pack.getType()
                || cargo.getArrCargoScheme()[0][0] == 0) {
            loadAlgorithm(cargo, pack);
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
