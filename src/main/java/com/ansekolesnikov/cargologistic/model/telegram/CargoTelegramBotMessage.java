package com.ansekolesnikov.cargologistic.model.telegram;

import com.ansekolesnikov.cargologistic.service.CargoLoadService;
import com.ansekolesnikov.cargologistic.service.CargoViewService;

public class CargoTelegramBotMessage {
    private CargoTelegramUserMessage userMessage;
    private Long chatId;
    private String userText;

    public CargoTelegramBotMessage(CargoTelegramUserMessage userMessage) {
        this.userMessage = userMessage;
        this.chatId = userMessage.getChatId();
        this.userText = userMessage.getText();
    }

    public String getAnswer() {
        String command = getCommand();
        switch (command) {
            case "load":
                return getTelegramCodeText(new CargoLoadService().runService(
                                userMessage.getInputFileName(),
                                userMessage.getInputAlgorithm(),
                                userMessage.getInputCountCards()
                        )
                );
            case "view":
                return getTelegramCodeText(new CargoViewService().runService(
                                userMessage.getInputFileName()
                        )
                );
            default:
                return "Не удалось определить введенную команду";
        }
    }

    private String getCommand() {
        return userMessage.getInputCommand();
    }

    private String getTelegramCodeText(String text) {
        return "```Ответ:\n" + text + "```";
    }
}
