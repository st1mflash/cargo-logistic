package com.ansekolesnikov.cargologistic.service.main.pack;

import com.ansekolesnikov.cargologistic.entity.pack.PackModel;
import com.ansekolesnikov.cargologistic.service.main.ResultServiceRun;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class ResultPackServiceRun extends ResultServiceRun {
    public void fillByPackModel(PackModel packModel) {
        super.setResultToString(packModel.toString());
        super.setResultToMap(packModel.toMap());
    }

    public void fillByListPackModel(List<PackModel> listPackModel) {
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