package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.service.CargoLoadService;
import com.ansekolesnikov.cargologistic.service.CargoViewService;
import com.ansekolesnikov.cargologistic.service.TelegramBotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {
    private CargoLoadService cargoLoadService;
    private CargoViewService cargoViewService;

    @Bean
    public CargoViewService cargoViewService() {
        return cargoViewService = new CargoViewService();
    }

    @Bean
    public CargoLoadService cargoLoadService() {
        return cargoLoadService = new CargoLoadService();
    }

    @Bean
    public TelegramBotService telegramBotService() {
        return new TelegramBotService();
    }
}
