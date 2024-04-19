package com.ansekolesnikov.cargologistic.handler;

import com.ansekolesnikov.cargologistic.service.CargoService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBotHandler extends TelegramLongPollingBot {
    private static final String BOT_USER_NAME = "ansekolesnikov_cargo_bot";
    private static final String TOKEN = "7142970649:AAHAvkbzHS-P6TwL8MPo7M0dJjDNM6hbX80";
    private final CargoService cargoViewService, cargoLoadService;

    public TelegramBotHandler(CargoService cargoViewService, CargoService cargoLoadService) {
        this.cargoViewService = cargoViewService;
        this.cargoLoadService = cargoLoadService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Обработка входящего сообщения
        Message message = update.getMessage();
        //sendMessage(message.getChatId(), );
        //sendMessage(message.getChatId(), "Привет, привет");
        sendMessage(message.getChatId(), cargoViewService.runService("one.json"));
        sendMessage(message.getChatId(), cargoLoadService.runService("def.txt", "max", "3"));
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
