package com.ansekolesnikov.cargologistic.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Message;

@NoArgsConstructor
@Data
public class TelegramUserMessage {
    private Message message;
    private Long chatId;
    private String text;
}
