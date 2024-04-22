package com.ansekolesnikov.cargologistic.handler;

import com.ansekolesnikov.cargologistic.model.telegram.TelegramMessageByBot;
import com.ansekolesnikov.cargologistic.model.telegram.TelegramMessageByUser;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramHandler extends TelegramLongPollingBot {
    private static final Logger LOGGER = Logger.getLogger(TelegramHandler.class.getName());
    private static final String BOT_USER_NAME = "ansekolesnikov_cargo_bot";
    private static final String TOKEN = "7142970649:AAHAvkbzHS-P6TwL8MPo7M0dJjDNM6hbX80";

    @Override
    public void onUpdateReceived(Update update) {
        TelegramMessageByUser userMessage = new TelegramMessageByUser(update.getMessage());
        sendMessage(userMessage.getChatId(), new TelegramMessageByBot(
                new TelegramMessageByUser(update.getMessage())).getAnswer()
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
