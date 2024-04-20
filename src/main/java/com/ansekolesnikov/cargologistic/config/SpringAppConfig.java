package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.service.CargoLoadService;
import com.ansekolesnikov.cargologistic.service.CargoService;
import com.ansekolesnikov.cargologistic.service.CargoViewService;
import com.ansekolesnikov.cargologistic.service.TelegramBotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {
    private CargoService cargoViewService, cargoLoadService;
    @Bean
    public CargoViewService cargoViewService() {
        return (CargoViewService) (cargoViewService = new CargoViewService());
    }

    @Bean
    public CargoLoadService cargoLoadService() {
        return (CargoLoadService) (cargoLoadService = new CargoLoadService());
    }

    @Bean
    public TelegramBotService telegramBotService() {
        return new TelegramBotService();
    }
}
