package com.ansekolesnikov.cargologistic.controller.rest.post;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/car_model")
public class PostCarModelRestApiController {
    @Autowired
    private CarService carService;

    @PostMapping
    public Map<String, String> createCarModel(@RequestBody Map<String, String> carModel) {
        return carService.runService(new CommandLine(
                                "car insert " + carModel.get("name") + " " +
                                        carModel.get("width") + " " +
                                        carModel.get("height")
                        )
                )
                .toMap();
    }
}
