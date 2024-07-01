package com.ansekolesnikov.cargologistic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "pack_model")
public class PackModelEntity {
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
}
