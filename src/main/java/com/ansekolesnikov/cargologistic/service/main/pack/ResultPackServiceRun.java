package com.ansekolesnikov.cargologistic.service.main.pack;

import com.ansekolesnikov.cargologistic.entity.pack.PackModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class ResultPackServiceRun {
    @Setter
    private String stringResult = "";
    private PackModel packModel;
    private List<PackModel> listPackModel;
    private Map<String, String> mapPackModel;
    private final List<Map<String, String>> listMapPackModel = new ArrayList<>();

    public void setPackModel(PackModel packModel) {
        this.packModel = packModel;
        stringResult = packModel.toString();
        mapPackModel = packModel.toMap();
    }

    public void setListPackModel(List<PackModel> listPackModel) {
        this.listPackModel = listPackModel;
        for (PackModel el : listPackModel) {
            stringResult = stringResult + el.toString() + "\n\n";
            listMapPackModel.add(el.toMap());
        }
    }
}
