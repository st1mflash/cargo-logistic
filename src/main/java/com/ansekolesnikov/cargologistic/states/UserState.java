package com.ansekolesnikov.cargologistic.states;

import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.enums.StateEnum;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.pages.ITelegramPage;
import lombok.Data;

@Data
public class UserState {
    private Long userId;
    private ITelegramPage page;
    private String requestString;
    private StateEnum currentState;
    private IRunnableByStringService service;
    private DatabaseOperationEnum operation;

    public UserState(Long userId, ITelegramPage page) {
        this.userId = userId;
        this.page = page;
    }
}
