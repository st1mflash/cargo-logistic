package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.service.cargo.load.LoadCargoService;
import com.ansekolesnikov.cargologistic.service.cargo.view.ViewCargoService;
import com.ansekolesnikov.cargologistic.service.telegram.TelegramService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {
    private static final Logger LOGGER = Logger.getLogger(SpringAppConfig.class.getName());
    private LoadCargoService loadCargoService;
    private ViewCargoService viewCargoService;
    private TelegramService telegramService;

    @Value("${telegram.bot.username}")
    private String TELEGRAM_BOT_USERNAME;
    @Value("${telegram.bot.token}")
    private String TELEGRAM_BOT_TOKEN;

    @Bean
    public ViewCargoService viewCargoService() {
        viewCargoService = new ViewCargoService();
        LOGGER.info("Сервис получения полной информации о грузовиках - успешно запущен!");
        return viewCargoService;
    }

    @Bean
    public LoadCargoService loadCargoService() {
        loadCargoService = new LoadCargoService();
        LOGGER.info("Сервис формирования поставки грузами из файла - успешно запущен!");
        return loadCargoService;
    }

    @Bean
    public TelegramService telegramService() {
        telegramService = new TelegramService();

        telegramService.startBot(TELEGRAM_BOT_TOKEN, TELEGRAM_BOT_USERNAME);

        LOGGER.info("Сервис работы телеграм ботов - успешно запущен!");
        return telegramService;
    }
}
