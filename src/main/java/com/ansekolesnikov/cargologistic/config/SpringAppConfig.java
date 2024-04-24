package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.service.main.load.LoadCarService;
import com.ansekolesnikov.cargologistic.service.main.view.ViewCarService;
import com.ansekolesnikov.cargologistic.service.telegram.TelegramService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {
    private static final Logger LOGGER = Logger.getLogger(SpringAppConfig.class.getName());
    private LoadCarService loadCarService;
    private ViewCarService viewCarService;
    private TelegramService telegramService;

    @Value("${telegram.bot.username}")
    private String TELEGRAM_BOT_USERNAME;
    @Value("${telegram.bot.token}")
    private String TELEGRAM_BOT_TOKEN;

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
        telegramService = new TelegramService();

        telegramService.startBot(TELEGRAM_BOT_TOKEN, TELEGRAM_BOT_USERNAME);

        LOGGER.info("Сервис работы телеграм ботов - успешно запущен!");
        return telegramService;
    }
}
