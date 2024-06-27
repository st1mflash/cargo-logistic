package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.constants.ButtonConstant;
import com.ansekolesnikov.cargologistic.service.PackModelService;
import com.ansekolesnikov.cargologistic.states.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TelegramGetPackParamNamePage implements ITelegramPage {
    @Override
    public SendMessage loadPage(UserState userState) {
        SendMessage message = new SendMessage();
        message.setText("Выберете параметр для обновления:");
        message.enableHtml(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(ButtonConstant.BTN_UPDATE_NAME_PACK);
        keyboardRows.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add(ButtonConstant.BTN_UPDATE_WIDTH_PACK);
        keyboardRow.add(ButtonConstant.BTN_UPDATE_HEIGHT_PACK);
        keyboardRows.add(keyboardRow);
        //if(userState.getService().getClass() == PackModelService.class) {
            keyboardRow = new KeyboardRow();
            keyboardRow.add(ButtonConstant.BTN_UPDATE_CODE_PACK);
            keyboardRow.add(ButtonConstant.BTN_UPDATE_SCHEME_PACK);
            keyboardRows.add(keyboardRow);
        //}
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        message.setReplyMarkup(replyKeyboardMarkup);
        return message;
    }
}
