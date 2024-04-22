package com.ansekolesnikov.cargologistic.model.telegram;

import com.ansekolesnikov.cargologistic.service.LoadCarService;
import com.ansekolesnikov.cargologistic.service.ViewCarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class TelegramMessageBot {
    private static final Logger LOGGER = Logger.getLogger(TelegramMessageBot.class.getName());
    private final TelegramMessageUser userMessage;
    private Long chatId;
    private String userText;

    public TelegramMessageBot(TelegramMessageUser userMessage) {
        this.userMessage = userMessage;
        this.chatId = userMessage.getChatId();
        this.userText = userMessage.getText();
    }

    public String getAnswer() {
        String command = getUserInputCommand();
        switch (command) {
            case "load":
                LOGGER.info("Запрос загрузки из файла '" + getUserInputFileName() + "' алгоритмом '" + getUserInputAlgorithm() + "' в " + getUserInputCountCars() + " ед. транспорта.");
                return getTelegramCodeText(new LoadCarService().runService(
                                userMessage.getInputFileName(),
                                userMessage.getInputAlgorithm(),
                                userMessage.getInputCountCars()
                        )
                );
            case "view":
                LOGGER.info("Запрос отображения информации о грузовиках из файла '" + getUserInputFileName() + "'");
                return getTelegramCodeText(new ViewCarService().runService(
                                userMessage.getInputFileName()
                        )
                );

            default:
                LOGGER.error("Не удалось определить введенную команду");
                return "Не удалось определить введенную команду";
        }
    }

    private String getUserInputCommand() {
        return userMessage.getInputCommand();
    }

    private String getUserInputAlgorithm() {
        return userMessage.getInputAlgorithm();
    }

    private String getUserInputCountCars() {
        return userMessage.getInputCountCars();
    }

    private String getUserInputFileName() {
        return userMessage.getInputFileName();
    }

    private String getTelegramCodeText(String text) {
        return "```Ответ:\n" + text + "```";
    }
}
