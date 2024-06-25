package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.constants.ButtonConstant;
import com.ansekolesnikov.cargologistic.service.PackModelService;
import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TelegramGetParamNamePage implements ITelegramPage {
    private final TelegramGetParamValuePage telegramGetParamValuePage;
    @Override
    public ITelegramPage nextPage() {
        return telegramGetParamValuePage;
    }

    @Override
    public SendMessage loadPageOnMessage(TelegramUserState userState, SendMessage message) {
        message.setText("Выберете параметр для обновления:");
        message.enableHtml(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(ButtonConstant.BTN_UPDATE_NAME);
        keyboardRows.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add(ButtonConstant.BTN_UPDATE_WIDTH);
        keyboardRow.add(ButtonConstant.BTN_UPDATE_HEIGHT);
        keyboardRows.add(keyboardRow);
        if(userState.getService().getClass() == PackModelService.class) {
            keyboardRow = new KeyboardRow();
            keyboardRow.add(ButtonConstant.BTN_UPDATE_CODE);
            keyboardRow.add(ButtonConstant.BTN_UPDATE_SCHEME);
            keyboardRows.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        message.setReplyMarkup(replyKeyboardMarkup);
        return message;
    }
}
