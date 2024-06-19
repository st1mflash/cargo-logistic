package com.ansekolesnikov.cargologistic;

public class MessageConstant {
    public static final String CAR_IMPORT_ERROR = "Ошибка импорта грузовиков из файла: ";
    public static final String PACK_IMPORT_ERROR = "Ошибка ошибка импорта грузов из файла: ";
    public static final String CAR_NOT_ENOUGH_ERROR = "Ошибка загрузки: недостаточно машин!";
    public static final String FILE_NOT_FOUND_ERROR = "Ошибка импорта: файл не найден.";
    public static final String FILE_FORMAT_NOT_FOUND_ERROR = "Ошибка импорта: не указан формат файла.";

    public static final String CAR_MODEL_INFO_REQUEST = "Запрос информации о модели авто.";
    public static final String CAR_MODEL_LIST_INFO_REQUEST = "Запрос информации о всех моделях авто.";
    public static final String CAR_MODEL_INSERT_REQUEST = "Добавление модели авто.";
    public static final String CAR_MODEL_UPDATE_REQUEST = "Обновление модели авто.";
    public static final String CAR_MODEL_DELETE_REQUEST = "Удаление модели авто.";
    public static final String CAR_COUNT_MUST_BE_MORE_ZERO = "Количество машин должно быть больше нуля";

    public static final String PACK_MODEL_INFO_REQUEST = "Запрос информации о модели посылки.";
    public static final String PACK_MODEL_LIST_INFO_REQUEST = "Запрос информации о всех моделях посылок.";
    public static final String PACK_MODEL_INSERT_REQUEST = "Добавление модели посылки.";
    public static final String PACK_MODEL_UPDATE_REQUEST = "Обновление модели посылки.";
    public static final String PACK_MODEL_DELETE_REQUEST = "Удаление модели посылки.";

    public static final String TELEGRAM_START_ERROR = "Ошибка запуска телеграм-бота. Подробнее: ";

    public static final String EMPTY_CAR_FILE = "Указанный файл не содержит информации о грузовиках.";

    public static final String SUCCESS_DELETE = "Удаление модели посылки.";
    public static final String UNKNOWN_COMMAND = "Не удалось определить команду.";
    public static final String UNKNOWN_ALGORITHM = "Введен неверный алгоритм. Доступные значения: 'max', 'half', 'type'.";
    public static final String COMMAND_ERROR = "Ошибка ввода команды. ";

}
