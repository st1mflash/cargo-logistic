package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.annotations.CargoPack;
import com.ansekolesnikov.cargologistic.dto.PackModelDto;

import java.util.List;
import java.util.Map;

@CargoPack
public interface IPackModelService {
    List<PackModelDto> getPackModelList();
    PackModelDto getPackModel(int id);
    PackModelDto addPackModel(PackModelDto packModelEntity);
    PackModelDto updatePackModel(PackModelDto packModelEntity);
    Map<String, String> deletePackModel(int id);
}
