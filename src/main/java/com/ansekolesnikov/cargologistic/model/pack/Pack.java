package com.ansekolesnikov.cargologistic.model.pack;

import com.ansekolesnikov.cargologistic.model.pack.utils.PackUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pack {
    private int carId = 0;
    private int id = 0;
    private String type;
    private String name;
    private int width;
    private int height;
    private String scheme;
    private Character code;

    public Pack(int type) {
        this.type = Integer.toString(type);
        this.width = new PackUtils().calcPackageWidthByType(type);

        switch (this.type) {
            case "1":
                scheme = "1";
                code = '1';
                width = 1;
                height = 1;
                break;
            case "2":
                scheme = "11";
                code = '2';
                width = 2;
                height = 2;
                break;
            case "3":
                scheme = "111";
                code = '3';
                width = 3;
                height = 1;
                break;
            case "4":
                scheme = "1111";
                code = '4';
                width = 4;
                height = 1;
                break;
            case "5":
                scheme = "11111";
                code = '5';
                width = 5;
                height = 1;
                break;
            case "6":
                scheme = "111111";
                code = '6';
                width = 3;
                height = 2;
                break;
            case "7":
                scheme = "11111110";
                code = '7';
                width = 4;
                height = 2;
                break;
            case "8":
                scheme = "11111111";
                code = '8';
                width = 4;
                height = 2;
                break;
            case "9":
                scheme = "111111111";
                code = '9';
                width = 3;
                height = 3;
                break;

        }
    }

    public Pack(
            int id,
            String name,
            Character code,
            String scheme,
            int width,
            int height
    ) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.scheme = scheme;
        this.code = code;
    }

    public Pack(
            String name,
            int width,
            int height,
            String scheme,
            Character code
    ) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.scheme = scheme;
        this.code = code;
    }

    public int calculateElements(){
        return scheme.replaceAll("0", "").length();
    }
}
