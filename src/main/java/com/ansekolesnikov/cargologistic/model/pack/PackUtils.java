package com.ansekolesnikov.cargologistic.model.pack;

public class PackUtils {
    public int calcPackageWidthByType(int packType) {
        return switch (packType) {
            case 1, 2, 3, 4, 5 -> packType;
            case 6, 9 -> 3;
            case 7, 8 -> 4;
            default -> 0;
        };
    }
}
