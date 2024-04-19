package com.ansekolesnikov.cargologistic.service.bot.telegram;

import com.ansekolesnikov.cargologistic.handler.TelegramBotHandler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class StartTelegramBotService {
    public StartTelegramBotService() {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(new TelegramBotHandler());
        } catch (TelegramApiException ignored) {}
    }
}
