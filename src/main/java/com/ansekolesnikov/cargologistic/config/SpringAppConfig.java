package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.service.LoadCarService;
import com.ansekolesnikov.cargologistic.service.ViewCarService;
import com.ansekolesnikov.cargologistic.service.TelegramService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {
    private LoadCarService loadCarService;
    private ViewCarService viewCarService;

    @Bean
    public ViewCarService viewCarService() {
        return viewCarService = new ViewCarService();
    }

    @Bean
    public LoadCarService loadCarService() {
        return loadCarService = new LoadCarService();
    }

    @Bean
    public TelegramService telegramService() {
        return new TelegramService();
    }
}
