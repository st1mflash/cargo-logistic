package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.dto.PackModelDto;
import com.ansekolesnikov.cargologistic.entity.PackModelEntity;

import java.util.List;
import java.util.Map;

public interface IPackModelService {
    List<PackModelDto> getPackModelList();
    PackModelDto getPackModel(int id);
    PackModelDto addPackModel(PackModelDto packModelEntity);
    PackModelDto updatePackModel(PackModelDto packModelEntity);
    Map<String, String> deletePackModel(PackModelDto packModelEntity);
}
