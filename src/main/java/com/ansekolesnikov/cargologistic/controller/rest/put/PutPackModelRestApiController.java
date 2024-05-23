package com.ansekolesnikov.cargologistic.controller.rest.put;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.pack.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/pack_model")
public class PutPackModelRestApiController {
    @Autowired
    private PackService packService;

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
}
