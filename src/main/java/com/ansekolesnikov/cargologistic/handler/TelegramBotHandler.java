package com.ansekolesnikov.cargologistic.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TelegramBotHandler extends TelegramLongPollingBot {
    private static final String BOT_USER_NAME = "ansekolesnikov_cargo_bot";
    private static final String TOKEN = "7142970649:AAHAvkbzHS-P6TwL8MPo7M0dJjDNM6hbX80";
    private TelegramLongPollingBot telegramLongPollingBot;

    /*
    @Autowired
    public TelegramBotHandler() {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(new TelegramBotHandler());
        } catch (TelegramApiException e) {
            //e.printStackTrace();
            return;
        }
    }
    */


    @Override
    public void onUpdateReceived(Update update) {
        // Обработка входящего сообщения
        Message message = update.getMessage();
        sendMessage(message.getChatId(), "Привет, привет");
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

        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
