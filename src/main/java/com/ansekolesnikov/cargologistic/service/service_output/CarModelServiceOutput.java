package com.ansekolesnikov.cargologistic.service.service_output;

import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class CarModelServiceOutput extends ServiceOutput {
    public void create(CarModel carModel) {
        super.setResultToString(carModel.toString());
        super.setResultToMap(carModel.toMap());
    }
    public void create(List<CarModel> listCarModel) {
        StringBuilder stringResult = new StringBuilder();
        List<Map<String, String>> listMapCarModel = new ArrayList<>();
        for (CarModel el : listCarModel) {
            stringResult.append(el.toString()).append("\n\n");
            listMapCarModel.add(el.toMap());
        }
        super.setResultToString(stringResult.toString());
        super.setResultToListMap(listMapCarModel);
    }
}
