package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.entity.PackModel;
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
    public PackModel getPackModel(@PathVariable int id) {
        return packModelService.getPackModel(id);
    }

    @GetMapping
    public List<PackModel> getListPackModels() {
        return packModelService.getPackModelList();
    }

    @PostMapping
    public PackModel createPackModel(@RequestBody PackModel packModel) {
        return packModelService.addPackModel(packModel);
    }

    @PutMapping
    public PackModel updatePackModel(@RequestBody PackModel packModel) {
        return packModelService.updatePackModel(packModel);
    }

    @DeleteMapping
    public Map<String, String> deletePackModel(@RequestBody PackModel packModel) {
        return packModelService.deletePackModel(packModel);
    }
}
