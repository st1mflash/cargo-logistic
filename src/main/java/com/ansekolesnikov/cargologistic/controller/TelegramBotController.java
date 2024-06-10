package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.entity.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.service.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
public class TelegramBotController extends TelegramLongPollingBot {
    private final TelegramBotService telegramBotService;
    private final String BOT_TOKEN;
    private final String BOT_USERNAME;
    private static final Logger LOGGER = Logger.getLogger(TelegramBotController.class.getName());

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
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
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
