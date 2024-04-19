package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.handler.TelegramBotHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class TelegramBotService {
    @Autowired
    public TelegramBotService(CargoService cargoViewService, CargoService cargoLoadService) {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(new TelegramBotHandler(cargoViewService, cargoLoadService));
        } catch (TelegramApiException ignored) {
        }
    }
}
