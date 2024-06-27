package com.ansekolesnikov.cargologistic.states;

import com.ansekolesnikov.cargologistic.enums.StateEnum;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.pages.ITelegramPage;
import lombok.Data;

@Data
public class TelegramState {
    private ITelegramPage page;
    private String requestString;
    private StateEnum currentState;
    private IRunnableByStringService service;

    public TelegramState(ITelegramPage page) {
        this.page = page;
    }
}
