package com.ansekolesnikov.cargologistic;

import com.ansekolesnikov.cargologistic.handler.TelegramBotHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class CargoLogisticApplication {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBotHandler());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        SpringApplication.run(CargoLogisticApplication.class, args);
    }

}
