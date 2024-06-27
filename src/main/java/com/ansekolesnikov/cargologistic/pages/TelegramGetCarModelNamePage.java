package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.states.TelegramState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Component
public class TelegramGetCarModelNamePage implements ITelegramPage {
    @Override
    public SendMessage loadPage(TelegramState telegramState) {
        SendMessage message = new SendMessage();
        message.setText("Введите название модели машины:");
        return message;
    }
}