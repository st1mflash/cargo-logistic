package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.entity.RequestString;
import com.ansekolesnikov.cargologistic.states.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Component
public class TelegramResultPage implements ITelegramPage {
    private final TelegramMenuPage telegramMenuPage;

    @Override
    public SendMessage loadPage(UserState userState) {
        SendMessage message = telegramMenuPage.loadPage(userState);
        message.setParseMode("Markdown");
        message.setText("```Ответ:\n" +
                userState.getService().run(
                        new RequestString(
                                userState.getService().getClass(),
                                userState.getRequestString())) + "```"
        );
        return message;
    }
}
