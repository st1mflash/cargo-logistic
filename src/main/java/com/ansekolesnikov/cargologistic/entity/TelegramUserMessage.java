package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.enums.ServiceCommandEnum;
import com.ansekolesnikov.cargologistic.utils.EntityUtils;
import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Message;

@Data
public class TelegramUserMessage {
    private Message message;
    private Long chatId;
    private String text;
    private ServiceCommandEnum command;

    public TelegramUserMessage(Message message) {
        this.message = message;
        this.chatId = message.getChatId();
        this.text = message.getText();
        this.command = EntityUtils.getServiceCommandEnum(this.text.split(" ")[0]);
    }
}
