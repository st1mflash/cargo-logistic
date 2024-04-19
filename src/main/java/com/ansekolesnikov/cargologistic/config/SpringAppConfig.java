package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.service.CargoLoadService;
import com.ansekolesnikov.cargologistic.service.CargoViewService;
import com.ansekolesnikov.cargologistic.service.bot.telegram.StartTelegramBotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {
    @Bean
    public CargoViewService cargoViewService() {
        return new CargoViewService();
    }

    @Bean
    public CargoLoadService cargoLoadService() {
        return new CargoLoadService();
    }

    @Bean
    public StartTelegramBotService startTelegramBotService() {
        return new StartTelegramBotService();
    }
}
