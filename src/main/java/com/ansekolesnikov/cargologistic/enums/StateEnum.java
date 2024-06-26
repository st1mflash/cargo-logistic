package com.ansekolesnikov.cargologistic.enums;

import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.pages.ITelegramPage;
import com.ansekolesnikov.cargologistic.pages.TelegramPages;
import com.ansekolesnikov.cargologistic.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public enum StateEnum {
    GET_LIST_PACK,
    GET_LIST_CAR,
    INPUT_ID_FOR_GET_PACK,
    INPUT_ID_FOR_GET_CAR,
    INPUT_ID_FOR_UPDATE_PACK,
    INPUT_ID_FOR_UPDATE_CAR,
    INPUT_ID_FOR_DELETE_PACK,
    INPUT_ID_FOR_DELETE_CAR,
    INPUT_NAME_FOR_INSERT_PACK,
    INPUT_NAME_FOR_INSERT_CAR,
    INPUT_NAME_FOR_UPDATE_PACK,
    INPUT_NAME_FOR_UPDATE_CAR,
    INPUT_WIDTH_FOR_INSERT_PACK,
    INPUT_WIDTH_FOR_INSERT_CAR,
    INPUT_WIDTH_FOR_UPDATE_PACK,
    INPUT_WIDTH_FOR_UPDATE_CAR,
    INPUT_HEIGHT_FOR_INSERT_PACK,
    INPUT_HEIGHT_FOR_INSERT_CAR,
    INPUT_HEIGHT_FOR_UPDATE_PACK,
    INPUT_HEIGHT_FOR_UPDATE_CAR,
    INPUT_CODE_FOR_INSERT_PACK,
    INPUT_CODE_FOR_UPDATE_PACK,
    INPUT_SCHEME_FOR_INSERT_PACK,
    INPUT_SCHEME_FOR_UPDATE_PACK,
    INPUT_PARAM_FOR_UPDATE_PACK,
    INPUT_PARAM_FOR_UPDATE_CAR;
}
