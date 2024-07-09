package com.ansekolesnikov.cargologistic.states;

import com.ansekolesnikov.cargologistic.enums.StateEnum;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.pages.ITelegramPage;
import lombok.Data;

@Data
public class TelegramUserState {
    private String command;
    private StateEnum state;
    private ITelegramPage page;
    private IRunnableByStringService service;

    public TelegramUserState(ITelegramPage page) {
        this.page = page;
    }
}
