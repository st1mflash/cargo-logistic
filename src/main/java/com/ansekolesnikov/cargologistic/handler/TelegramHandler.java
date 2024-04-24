package com.ansekolesnikov.cargologistic.handler;

import com.ansekolesnikov.cargologistic.model.telegram.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.service.telegram.TelegramService;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramHandler extends TelegramLongPollingBot {
    private static final Logger LOGGER = Logger.getLogger(TelegramHandler.class.getName());
    private final TelegramService telegramService;
    private final String TOKEN;
    private final String BOT_USER_NAME;

    public TelegramHandler(TelegramService telegramService, String bot_token, String bot_username) {
        this.telegramService = telegramService;
        this.BOT_USER_NAME = bot_username;
        this.TOKEN = bot_token;

        LOGGER.info("Телеграм бот @" + this.BOT_USER_NAME + " запущен и готов принимать запросы!");
    }

    @Override
    public void onUpdateReceived(Update update) {
        TelegramUserMessage userMessage = new TelegramUserMessage(update.getMessage());
        sendMessage(
                userMessage.getChatId(),
                telegramService.getAnswer(userMessage)
        );
    }

    @Override
    public String getBotUsername() {
        return BOT_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
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
