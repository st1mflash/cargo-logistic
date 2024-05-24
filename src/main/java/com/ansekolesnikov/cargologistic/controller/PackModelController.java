package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.pack.PackService;
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
    private PackService packService;

    @GetMapping("/{id}")
    public Map<String, String> getPackModel(@PathVariable int id) {
        CommandLine command = new CommandLine("pack get " + id);
        return packService.runService(command)
                .toMap();
    }

    @GetMapping
    public List<Map<String, String>> getListPackModels() {
        CommandLine command = new CommandLine("pack list");
        return packService.runService(command)
                .toListMap();
    }

    @PostMapping
    public Map<String, String> createPackModel(@RequestBody Map<String, String> packModel) {
        CommandLine command = new CommandLine(
                "pack insert " + packModel.get("name") + " " +
                        packModel.get("code") + " " +
                        packModel.get("scheme") + " " +
                        packModel.get("width") + " " +
                        packModel.get("height")
        );
        return packService.runService(command)
                .toMap();
    }

    @PutMapping
    public Map<String, String> updatePackModel(@RequestBody Map<String, String> packModel) {
        Map<String, String> result = new HashMap<>();
        try {
            for (String key : packModel.keySet()) {
                if (!Objects.equals(key, "id")) {
                    CommandLine command = new CommandLine("pack update " + packModel.get("id") + " " +
                            key + " " + packModel.get(key)
                    );
                    result = packService.runService(command).toMap();
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
        CommandLine command = new CommandLine("pack delete " + packModel.get("id"));
        Map<String, String> result = new HashMap<>();
        if(packService.runService(command).toMap() != null) {
            result.put("status", "success");
        } else {
            result.put("status", "fail");
        }
        return result;
    }
}
