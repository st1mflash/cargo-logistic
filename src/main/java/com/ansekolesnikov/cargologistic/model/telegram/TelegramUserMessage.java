package com.ansekolesnikov.cargologistic.model.telegram;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Message;

@Getter
public class TelegramUserMessage {
    private final Message message;
    private final Long chatId;
    private final String text;

    public TelegramUserMessage(Message message) {
        this.message = message;
        this.chatId = message.getChatId();
        this.text = message.getText();
    }

    public String getInputCommand() {
        return this.text.toLowerCase().split(" ")[0];
    }

}
