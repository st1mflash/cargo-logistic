package com.ansekolesnikov.cargologistic.states;

import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.pages.ITelegramPage;
import lombok.Data;

@Data
public class TelegramUserState {
    private Long userId;
    private ITelegramPage page;
    private String requestString;
    private IRunnableByStringService service;
    private DatabaseOperationEnum operation;

    public TelegramUserState(Long userId, ITelegramPage page) {
        this.userId = userId;
        this.page = page;
    }
}
