package com.ansekolesnikov.cargologistic.service.service_output;

import com.ansekolesnikov.cargologistic.entity.PackModelEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class PackModelServiceOutput extends ServiceOutput {
    public void create(PackModelEntity packModelEntity) {
        super.setResultToString(packModelEntity.toString());
        super.setResultToMap(packModelEntity.toMap());
    }

    public void create(List<PackModelEntity> listPackModelEntity) {
        StringBuilder stringResult = new StringBuilder();
        List<Map<String, String>> listMapPackModel = new ArrayList<>();
        for (PackModelEntity el : listPackModelEntity) {
            stringResult.append(el.toString()).append("\n\n");
            listMapPackModel.add(el.toMap());
        }
        super.setResultToString(stringResult.toString());
        super.setResultToListMap(listMapPackModel);
    }
}
