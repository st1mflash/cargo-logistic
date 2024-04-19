package com.ansekolesnikov.cargologistic.model;
public class CargoPackage {
    private int idCargo = 0;
    private final int type;
    private int width;

    public CargoPackage(int type) {
        this.type = type;

        //initArrayPackageScheme();
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

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public int getWidth() {
        return width;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public int getType() {
        return type;
    }
}
