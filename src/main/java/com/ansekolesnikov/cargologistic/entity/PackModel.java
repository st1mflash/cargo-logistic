package com.ansekolesnikov.cargologistic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pack_model")
public class PackModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;
    @Column(name = "name")
    protected String name;
    @Column(name = "code")
    protected Character code;
    @Column(name = "scheme")
    protected String scheme;
    @Column(name = "scheme_width")
    protected int width;
    @Column(name = "scheme_height")
    protected int height;

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
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        map.put("name", name);
        map.put("code", String.valueOf(code));
        map.put("scheme", scheme);
        map.put("width", String.valueOf(width));
        map.put("height", String.valueOf(height));
        return map;
    }

    public String toString() {
        return "Идентификатор: #" + id
                + "\nНазвание посылки: " + name
                + "\nПараметры посылки: " + width + "x" + height
                + "\nСхема посылки:\n"
                + toStringPackScheme();
    }

    public String toStringPackScheme() {
        StringBuilder packSchemeToString = new StringBuilder();
        String packScheme = scheme;
        for (int i = height - 1; i >= 0; i--) {
            packSchemeToString.append("+");
            for (int j = 0; j < width; j++) {
                if (Objects.equals("" + packScheme.charAt(i * width + j), "0")) {
                    packSchemeToString.append(" ");
                } else {
                    packSchemeToString.append(code);
                }
            }
            packSchemeToString.append("+\n");
        }
        packSchemeToString.append("+".repeat(Math.max(0, width + 2)));
        return packSchemeToString.toString();
    }

    public int calculateCountElements() {
        return scheme.replaceAll("0", "").length();
    }
}
