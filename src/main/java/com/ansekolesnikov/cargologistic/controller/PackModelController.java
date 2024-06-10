package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.dto.PackModelDto;
import com.ansekolesnikov.cargologistic.service.PackModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/pack_model")
public class PackModelController {
    private final PackModelService packModelService;

    @GetMapping("/{id}")
    public PackModelDto getPackModel(@PathVariable int id) {
        return packModelService.getPackModel(id);
    }

    @GetMapping
    public List<PackModelDto> getListPackModels() {
        return packModelService.getPackModelList();
    }

    @PostMapping
    public PackModelDto createPackModel(@RequestBody PackModelDto packModelDto) {
        return packModelService.addPackModel(packModelDto);
    }

    @PutMapping
    public PackModelDto updatePackModel(@RequestBody PackModelDto packModelDto) {
        return packModelService.updatePackModel(packModelDto);
    }

    @DeleteMapping
    public Map<String, String> deletePackModel(@RequestBody PackModelDto packModelDto) {
        return packModelService.deletePackModel(packModelDto);
    }
}
