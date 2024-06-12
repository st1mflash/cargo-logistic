package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.enums.ServiceCommandEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Message;

@NoArgsConstructor
@Data
public class TelegramUserMessage {
    private Message message;
    private Long chatId;
    private String text;
    private ServiceCommandEnum command;
}
