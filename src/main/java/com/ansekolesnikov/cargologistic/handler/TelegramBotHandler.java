package com.ansekolesnikov.cargologistic.handler;

import com.ansekolesnikov.cargologistic.model.telegram.CargoTelegramBotMessage;
import com.ansekolesnikov.cargologistic.model.telegram.CargoTelegramUserMessage;
import com.ansekolesnikov.cargologistic.service.CargoService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBotHandler extends TelegramLongPollingBot {
    private static final String BOT_USER_NAME = "ansekolesnikov_cargo_bot";
    private static final String TOKEN = "7142970649:AAHAvkbzHS-P6TwL8MPo7M0dJjDNM6hbX80";
    private Message outputMessage;

    public TelegramBotHandler(CargoService cargoViewService, CargoService cargoLoadService) {
        //this.cargoViewService = cargoViewService;
        //this.cargoLoadService = cargoLoadService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        CargoTelegramUserMessage userMessage = new CargoTelegramUserMessage(update.getMessage());
        sendMessage(userMessage.getChatId(), new CargoTelegramBotMessage(new CargoTelegramUserMessage(update.getMessage())).getAnswer());
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
            e.printStackTrace();
        }
    }
}
