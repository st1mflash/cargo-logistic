package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.entity.PackModel;

import java.util.List;
import java.util.Map;

public interface IPackModelService {
    List<PackModel> getPackModelList();
    PackModel getPackModel(int id);
    PackModel addPackModel(PackModel packModel);
    PackModel updatePackModel(PackModel packModel);
    Map<String, String> deletePackModel(PackModel packModel);
}
