package com.ansekolesnikov.cargologistic.model;

public class Pack {
    private int carId = 0;
    private final int type;
    private int width;

    public Pack(int type) {
        this.type = type;
        initPackageWidth();
    }

    private void initPackageWidth() {
        switch (type) {
            case 1, 2, 3, 4, 5:
                width = type;
                break;
            case 6, 9:
                width = 3;
                break;
            case 7, 8:
                width = 4;
                break;
            default:
                break;
        }
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getWidth() {
        return width;
    }

    public int getCarId() {
        return carId;
    }

    public int getType() {
        return type;
    }
}
