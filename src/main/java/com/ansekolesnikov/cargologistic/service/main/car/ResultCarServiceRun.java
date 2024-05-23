package com.ansekolesnikov.cargologistic.service.main.car;

import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import com.ansekolesnikov.cargologistic.service.main.ResultServiceRun;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class ResultCarServiceRun extends ResultServiceRun {
    public void fillByCarModel(CarModel carModel) {
        super.setResultToString(carModel.toString());
        super.setResultToMap(carModel.toMap());
    }
    public void fillByListCarModel(List<CarModel> listCarModel) {
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
