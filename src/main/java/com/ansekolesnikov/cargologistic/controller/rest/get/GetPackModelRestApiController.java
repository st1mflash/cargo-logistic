package com.ansekolesnikov.cargologistic.controller.rest.get;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/pack_model")
public class GetPackModelRestApiController {
    @GetMapping
    public String getListPackModels() {
        return "";
    }

    @GetMapping("/{id}")
    public String getPackModel() {
        return "";
    }
}
