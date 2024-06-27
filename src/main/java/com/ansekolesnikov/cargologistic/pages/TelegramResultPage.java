package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.entity.RequestString;
import com.ansekolesnikov.cargologistic.states.TelegramState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Component
public class TelegramResultPage implements ITelegramPage {
    private final TelegramMenuPage telegramMenuPage;

    @Override
    public SendMessage loadPage(TelegramState telegramState) {
        SendMessage message = telegramMenuPage.loadPage(telegramState);
        message.setParseMode("Markdown");
        message.setText("```Ответ:\n" +
                telegramState.getService().run(
                        new RequestString(
                                telegramState.getService().getClass(),
                                telegramState.getRequestString())) + "```"
        );
        return message;
    }
}
