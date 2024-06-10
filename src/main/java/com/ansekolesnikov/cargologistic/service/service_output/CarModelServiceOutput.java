package com.ansekolesnikov.cargologistic.service.service_output;

import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class CarModelServiceOutput extends ServiceOutput {
    public void create(CarModelEntity carModelEntity) {
        super.setResultToString(carModelEntity.toString());
        super.setResultToMap(carModelEntity.toMap());
    }
    public void create(List<CarModelEntity> listCarModelEntity) {
        StringBuilder stringResult = new StringBuilder();
        List<Map<String, String>> listMapCarModel = new ArrayList<>();
        for (CarModelEntity el : listCarModelEntity) {
            stringResult.append(el.toString()).append("\n\n");
            listMapCarModel.add(el.toMap());
        }
        super.setResultToString(stringResult.toString());
        super.setResultToListMap(listMapCarModel);
    }
}
