package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.service.PackModelService;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/pack_model")
public class PackModelController {
    @Autowired
    private PackModelService packModelService;

    @GetMapping("/{id}")
    public Map<String, String> getPackModel(@PathVariable int id) {
        ServiceInput command = new ServiceInput("pack get " + id);
        return packModelService.runService(command)
                .toMap();
    }

    @GetMapping
    public List<Map<String, String>> getListPackModels() {
        ServiceInput command = new ServiceInput("pack list");
        return packModelService.runService(command)
                .toListMap();
    }

    @PostMapping
    public Map<String, String> createPackModel(@RequestBody Map<String, String> packModel) {
        ServiceInput command = new ServiceInput(
                "pack insert " + packModel.get("name") + " " +
                        packModel.get("code") + " " +
                        packModel.get("scheme") + " " +
                        packModel.get("width") + " " +
                        packModel.get("height")
        );
        return packModelService.runService(command)
                .toMap();
    }

    @PutMapping
    public Map<String, String> updatePackModel(@RequestBody Map<String, String> packModel) {
        Map<String, String> result = new HashMap<>();
        try {
            for (String key : packModel.keySet()) {
                if (!Objects.equals(key, "id")) {
                    ServiceInput command = new ServiceInput("pack update " + packModel.get("id") + " " +
                            key + " " + packModel.get(key)
                    );
                    result = packModelService.runService(command).toMap();
                }
            }
            return result;
        } catch (RuntimeException e) {
            result.clear();
            result.put("status", "fail");
            return result;
        }
    }

    @DeleteMapping
    public Map<String, String> deletePackModel(@RequestBody Map<String, String> packModel) {
        ServiceInput command = new ServiceInput("pack delete " + packModel.get("id"));
        Map<String, String> result = new HashMap<>();
        if(packModelService.runService(command).toMap() != null) {
            result.put("status", "success");
        } else {
            result.put("status", "fail");
        }
        return result;
    }
}
