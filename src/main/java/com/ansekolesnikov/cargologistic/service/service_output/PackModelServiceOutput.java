package com.ansekolesnikov.cargologistic.service.service_output;

import com.ansekolesnikov.cargologistic.entity.pack.PackModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class PackModelServiceOutput extends ServiceOutput {
    public void create(PackModel packModel) {
        super.setResultToString(packModel.toString());
        super.setResultToMap(packModel.toMap());
    }

    public void create(List<PackModel> listPackModel) {
        StringBuilder stringResult = new StringBuilder();
        List<Map<String, String>> listMapPackModel = new ArrayList<>();
        for (PackModel el : listPackModel) {
            stringResult.append(el.toString()).append("\n\n");
            listMapPackModel.add(el.toMap());
        }
        super.setResultToString(stringResult.toString());
        super.setResultToListMap(listMapPackModel);
    }
}
