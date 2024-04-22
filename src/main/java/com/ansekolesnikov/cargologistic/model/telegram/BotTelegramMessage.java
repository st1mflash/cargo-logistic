package com.ansekolesnikov.cargologistic.model.telegram;

import com.ansekolesnikov.cargologistic.service.LoadCarService;
import com.ansekolesnikov.cargologistic.service.ViewCarService;


public class BotTelegramMessage {
    private UserTelegramMessage userMessage;
    private Long chatId;
    private String userText;

    public BotTelegramMessage(UserTelegramMessage userMessage) {
        this.userMessage = userMessage;
        this.chatId = userMessage.getChatId();
        this.userText = userMessage.getText();
    }

    public String getAnswer() {
        String command = getCommand();
        switch (command) {
            case "load":
                return getTelegramCodeText(new LoadCarService().runService(
                                userMessage.getInputFileName(),
                                userMessage.getInputAlgorithm(),
                                userMessage.getInputCountCards()
                        )
                );
            case "view":
                return getTelegramCodeText(new ViewCarService().runService(
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
