package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.enums.ServiceCommandEnum;
import com.ansekolesnikov.cargologistic.utils.EntityUtils;
import lombok.Data;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Message;

@Data
public class TelegramUserMessage {
    private final Message message;
    private final Long chatId;
    private final String text;
    private final ServiceCommandEnum command;

    public TelegramUserMessage(Message message) {
        this.message = message;
        this.chatId = message.getChatId();
        this.text = message.getText();
        this.command = EntityUtils.getServiceCommandEnum(this.text.split(" ")[0]);
    }
}
