package com.ansekolesnikov.cargologistic.controller.rest.post;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.pack.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/pack_model")
public class PostPackModelRestApiController {
    @Autowired
    private PackService packService;

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
}
