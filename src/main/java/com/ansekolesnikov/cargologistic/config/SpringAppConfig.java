package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.service.LoadCarService;
import com.ansekolesnikov.cargologistic.service.ViewCarService;
import com.ansekolesnikov.cargologistic.service.TelegramService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {
    private static final Logger LOGGER = Logger.getLogger(SpringAppConfig.class.getName());
    private LoadCarService loadCarService;
    private ViewCarService viewCarService;

    @Bean
    public ViewCarService viewCarService() {
        viewCarService = new ViewCarService();
        LOGGER.info("Сервис получения полной информации о грузовиках - успешно запущен!");
        return viewCarService;
    }

    @Bean
    public LoadCarService loadCarService() {
        loadCarService = new LoadCarService();
        LOGGER.info("Сервис формирования поставки грузами из файла - успешно запущен!");
        return loadCarService;
    }

    @Bean
    public TelegramService telegramService() {
        TelegramService telegramService = new TelegramService();
        LOGGER.info("Сервис работы телеграм бота - успешно запущен!");
        return telegramService;
    }

}
