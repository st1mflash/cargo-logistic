package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.dto.PackModelDto;

import java.util.List;
import java.util.Map;

public interface IPackModelService {
    List<PackModelDto> getPackModelList();

    PackModelDto getPackModel(int id);

    PackModelDto addPackModel(PackModelDto packModelEntity);

    PackModelDto updatePackModel(PackModelDto packModelEntity);

    Map<String, String> deletePackModel(int id);
}
