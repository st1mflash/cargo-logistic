package com.ansekolesnikov.cargologistic.constants;

import org.telegram.telegrambots.meta.api.objects.Message;

public class ButtonConstant {
    public static final String BTN_PACK_LIST = "Посылки";
    public static final String BTN_CAR_LIST = "Машины";
    public static final String BTN_GET_PACK = "Получить посылку";
    public static final String BTN_GET_CAR = "Получить машину";
    public static final String BTN_INSERT_PACK = "Добавить посылку";
    public static final String BTN_INSERT_CAR = "Добавить машину";
    public static final String BTN_UPDATE_PACK = "Изменить посылку";
    public static final String BTN_UPDATE_CAR = "Изменить машину";
    public static final String BTN_DELETE_PACK = "Удалить посылку";
    public static final String BTN_DELETE_CAR = "Удалить машину";
    public static final String BTN_LOAD_FILE = "Загрузка машин из файла";
    public static final String BTN_LOAD_LIST = "Ручная загрузка машин";
    public static final String BTN_VIEW_FILE = "Отображение машин из файла";

    public static final String BTN_UPDATE_NAME_PACK = "Название посылки";
    public static final String BTN_UPDATE_WIDTH_PACK = "Ширина посылки";
    public static final String BTN_UPDATE_HEIGHT_PACK = "Высота посылки";
    public static final String BTN_UPDATE_CODE_PACK = "Код посылки";
    public static final String BTN_UPDATE_SCHEME_PACK = "Схема посылки";

    public static final String BTN_UPDATE_NAME_CAR = "Название машины";
    public static final String BTN_UPDATE_WIDTH_CAR = "Ширина машины";
    public static final String BTN_UPDATE_HEIGHT_CAR = "Высота машины";

    public static final String BTN_ALGORITHM_MAX = "Максимально";
    public static final String BTN_ALGORITHM_HALF = "Половина кузова";
    public static final String BTN_ALGORITHM_TYPE = "По типу посылки";

    public static boolean isButtonWithoutAppendCommand(Message message) {
        return switch (message.getText()) {
            case BTN_PACK_LIST,
                 BTN_CAR_LIST,
                 BTN_GET_PACK,
                 BTN_GET_CAR,
                 BTN_INSERT_PACK,
                 BTN_INSERT_CAR,
                 BTN_DELETE_PACK,
                 BTN_DELETE_CAR,
                 BTN_UPDATE_PACK,
                 BTN_UPDATE_CAR,
                 BTN_LOAD_FILE,
                 BTN_LOAD_LIST,
                 BTN_VIEW_FILE -> true;
            default -> false;
        };
    }
}
