package com.ansekolesnikov.cargologistic.controller.rest.get;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.pack.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/pack_model")
public class GetPackModelRestApiController {
    @Autowired
    private PackService packService;
    @GetMapping
    public List<Map<String, String>> getListPackModels() {
        CommandLine command = new CommandLine("pack list");
        return packService.runService(command)
                .toListMap();
    }

    @GetMapping("/{id}")
    public Map<String, String> getPackModel(@PathVariable int id) {
        CommandLine command = new CommandLine("pack get " + id);
        return packService.runService(command)
                .toMap();
    }
}
