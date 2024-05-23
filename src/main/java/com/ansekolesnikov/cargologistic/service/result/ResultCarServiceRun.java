package com.ansekolesnikov.cargologistic.service.result;

import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class ResultCarServiceRun {
    @Setter
    private String stringResult = "";
    private CarModel carModel;
    private List<CarModel> listCarModel;
    private Map<String, String> mapCarModel;
    private final List<Map<String, String>> listMapCarModel = new ArrayList<>();

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
        stringResult = carModel.toString();
        mapCarModel = carModel.toMap();
    }

    public void setListCarModel(List<CarModel> listCarModel) {
        this.listCarModel = listCarModel;
        for (CarModel el : listCarModel) {
            stringResult = stringResult + el.toString() + "\n\n";
            listMapCarModel.add(el.toMap());
        }
    }
}
