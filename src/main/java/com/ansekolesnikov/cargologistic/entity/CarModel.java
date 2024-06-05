package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
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
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer idModel;
    @Column(name = "name")
    protected String nameModel;
    @Column(name = "cargo_width")
    protected int cargoWidthModel;
    @Column(name = "cargo_height")
    protected int cargoHeightModel;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(idModel));
        map.put("name", nameModel);
        map.put("width", String.valueOf(cargoWidthModel));
        map.put("height", String.valueOf(cargoHeightModel));
        return map;
    }

    public String toString() {
        return "Идентификатор: #" + idModel
                + "\nНазвание модели: " + nameModel
                + "\nПараметры кузова: " + cargoWidthModel + "x" + cargoHeightModel;
    }
}
