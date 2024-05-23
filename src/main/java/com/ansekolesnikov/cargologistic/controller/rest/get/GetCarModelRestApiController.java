package com.ansekolesnikov.cargologistic.controller.rest.get;


import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/car_model")
public class GetCarModelRestApiController {
    @Autowired
    private CarService carService;
    @GetMapping
    public List<Map<String, String>> getListCarModels() {
        return carService.runService(new CommandLine("car list"))
                .getResultCarServiceRun()
                .getListMapCarModel();
    }

    @GetMapping("/{id}")
    public Map<String, String> getCarModel(@PathVariable int id) {
        return carService.runService(new CommandLine("car get " + id))
                .getResultCarServiceRun()
                .getMapCarModel();
    }
}
