package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.dto.CarModelDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        map.put("name", name);
        map.put("width", String.valueOf(width));
        map.put("height", String.valueOf(height));
        return map;
    }

    public String toString() {
        return "Идентификатор: #" + id
                + "\nНазвание модели: " + name
                + "\nПараметры кузова: " + width + "x" + height;
    }

    public static CarModelEntity to(CarModelDto carModelDto) {
        CarModelEntity carModelEntity = new CarModelEntity();
        carModelEntity.id = carModelDto.getId();
        carModelEntity.name = carModelDto.getName();
        carModelEntity.width = carModelDto.getWidth();
        carModelEntity.height = carModelDto.getHeight();
        return carModelEntity;
    }
}