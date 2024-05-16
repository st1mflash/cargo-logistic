package com.ansekolesnikov.cargologistic.model.pack;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pack_model")
public class PackModel {
    @Id
    private int id = 0;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private Character code;
    @Column(name = "scheme")
    private String scheme;
    @Column(name = "scheme_width")
    private int width;
    @Column(name = "scheme_height")
    private int height;

    private int carId = 0;
    private String[][] arrScheme;

    public PackModel(int code) {
        this.code = Integer.toString(code).charAt(0);
        initArrScheme();
    }

    public PackModel(
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
        initArrScheme();
    }

    public PackModel(
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
        initArrScheme();
    }

    public int calculateElements() {
        return scheme.replaceAll("0", "").length();
    }

    public void initArrScheme() {
        arrScheme = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (scheme.charAt(i * width + j) == '1') {
                    arrScheme[i][j] = String.valueOf(code);
                } else {
                    arrScheme[i][j] = "0";
                }
            }
        }
    }
}
