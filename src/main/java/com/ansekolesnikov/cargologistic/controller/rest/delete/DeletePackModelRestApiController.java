package com.ansekolesnikov.cargologistic.controller.rest.delete;


import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.pack.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/pack_model")
public class DeletePackModelRestApiController {
    @Autowired
    private PackService packService;

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
