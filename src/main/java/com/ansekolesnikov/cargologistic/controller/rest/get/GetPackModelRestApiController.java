package com.ansekolesnikov.cargologistic.controller.rest.get;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.pack.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        return packService.runService(new CommandLine("pack list"))
                .getResultPackServiceRun()
                .getListMapPackModel();
    }

    @GetMapping("/{id}")
    public Map<String, String> getPackModel() {
        return null;
    }
}
