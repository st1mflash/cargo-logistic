package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.handler.TelegramHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class TelegramService {
    private static final Logger LOGGER = Logger.getLogger(TelegramService.class.getName());
    @Autowired
    public TelegramService() {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(new TelegramHandler());
            LOGGER.info("Сервис работы телеграм бота - успешно запущен!");
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка запуска телеграм-бота. Подробнее: " + e);
        }
    }
}
