package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.annotations.CargoCar;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@CargoCar //todo для чего интерфейс к сущности
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "car_model")
public class CarModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;
    @Column(name = "name")
    protected String name;
    @Column(name = "cargo_width")
    protected int width;
    @Column(name = "cargo_height")
    protected int height;

    public String toString() {
        return "Идентификатор: #" + id
                + "\nНазвание модели: " + name
                + "\nПараметры кузова: " + width + "x" + height;
    }
}
