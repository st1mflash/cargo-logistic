package com.ansekolesnikov.cargologistic.entity.pack;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public int calculateCountElements() {
        return scheme.replaceAll("0", "").length();
    }
}
