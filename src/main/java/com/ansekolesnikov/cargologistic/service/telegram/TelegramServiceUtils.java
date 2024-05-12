package com.ansekolesnikov.cargologistic.service.telegram;

import org.springframework.stereotype.Component;

@Component
public class TelegramServiceUtils {
    public String formatToCodeStyle(String text) {
        return "```Ответ:\n" + text + "```";
    }
}
