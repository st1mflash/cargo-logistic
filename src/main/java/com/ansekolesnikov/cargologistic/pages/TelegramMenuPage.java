package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.constants.ButtonConstant;
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
public class TelegramMenuPage implements ITelegramPage {

    @Override
    public ITelegramPage nextPage() {
        return null;
    }

    @Override
    public SendMessage loadPageOnMessage(TelegramUserState userState, SendMessage message) {
        message.setText("Выберите опцию:");
        message.enableHtml(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(ButtonConstant.BTN_PACK_LIST);
        keyboardRow.add(ButtonConstant.BTN_CAR_LIST);
        keyboardRows.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add(ButtonConstant.BTN_GET_PACK);
        keyboardRow.add(ButtonConstant.BTN_GET_CAR);
        keyboardRows.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add(ButtonConstant.BTN_INSERT_PACK);
        keyboardRow.add(ButtonConstant.BTN_INSERT_CAR);
        keyboardRows.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add(ButtonConstant.BTN_UPDATE_PACK);
        keyboardRow.add(ButtonConstant.BTN_UPDATE_CAR);
        keyboardRows.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add(ButtonConstant.BTN_DELETE_PACK);
        keyboardRow.add(ButtonConstant.BTN_DELETE_CAR);
        keyboardRows.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add(ButtonConstant.BTN_LOAD_FILE);
        keyboardRow.add(ButtonConstant.BTN_LOAD_LIST);
        keyboardRows.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add(ButtonConstant.BTN_VIEW_FILE);
        keyboardRows.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        message.setReplyMarkup(replyKeyboardMarkup);
        return message;
    }
}
