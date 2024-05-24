package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.entity.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.service.TelegramBotService;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBotController extends TelegramLongPollingBot {
    private static final Logger LOGGER = Logger.getLogger(TelegramBotController.class.getName());
    private final TelegramBotService telegramBotService;
    private final String token;
    private final String username;

    public TelegramBotController(
            TelegramBotService telegramBotService,
            String bot_token,
            String bot_username
    ) {
        this.telegramBotService = telegramBotService;
        this.username = bot_username;
        this.token = bot_token;

        LOGGER.info("Телеграм бот @" + this.username + " запущен и готов принимать запросы.");
    }

    @Override
    public void onUpdateReceived(Update update) {
        TelegramUserMessage userMessage = new TelegramUserMessage(update.getMessage());
        sendMessage(
                userMessage.getChatId(),
                telegramBotService.toStringBotAnswer(userMessage)
        );
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();

        message.setParseMode("Markdown");
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка отправки сообщения. Подробнее: " + e);
        }
    }
}
