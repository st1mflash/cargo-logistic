package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.states.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Component
public class TelegramGetPackModelNamePage implements ITelegramPage {
    @Override
    public SendMessage loadPage(UserState userState) {
        SendMessage message = new SendMessage();
        message.setText("Введите список посылок:");
        return message;
    }
}
