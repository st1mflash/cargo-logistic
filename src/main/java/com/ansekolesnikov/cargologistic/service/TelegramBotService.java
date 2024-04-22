package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.handler.TelegramBotHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class TelegramBotService {
    private static final Logger LOGGER = Logger.getLogger(TelegramBotService.class.getName());
    private CargoLoadService cargoLoadService;
    private CargoViewService cargoViewService;
    @Autowired
    public TelegramBotService() {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(new TelegramBotHandler());
            LOGGER.info("Сервис работы телеграм бота - успешно запущен!");
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка запуска телеграм-бота. Подробнее: " + e);
        }
    }
}
