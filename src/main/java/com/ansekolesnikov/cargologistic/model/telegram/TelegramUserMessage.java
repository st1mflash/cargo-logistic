package com.ansekolesnikov.cargologistic.model.telegram;

import com.ansekolesnikov.cargologistic.enums.ServiceCommandEnum;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Message;

@Getter
public class TelegramUserMessage {
    private final Message message;
    private final Long chatId;
    private final String text;
    private final ServiceCommandEnum command;

    public TelegramUserMessage(Message message) {
        this.message = message;
        this.chatId = message.getChatId();
        this.text = message.getText();
        this.command = ServiceCommandEnum.initEnumFromString(this.text.split(" ")[0]);
    }
}
