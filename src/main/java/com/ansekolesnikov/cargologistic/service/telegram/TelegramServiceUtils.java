package com.ansekolesnikov.cargologistic.service.telegram;

import org.springframework.stereotype.Component;

@Component
public class TelegramServiceUtils {
    public String toStringBotInfo() {
        String info = """
                Доступные команды:

                Загрузка машин данными из файла:
                load_file [название файла] [алгоритм: max/half/type] [кол-во машин]

                Загрузка машин введенными данными:
                load_list [модель машины] [алгоритм: max/half/type] [кол-во машин]:
                [модель посылки]
                [модель посылки]
                [ ... ]

                Отображение из файла загруженных машин:
                view_file [название файла]

                Отображение всех моделей машин:
                car list

                Добавление новой модели машины:
                car insert [название] [ширина кузова] [высота кузова]

                Обновление модели машины:
                car update [ID машины] [параметр: name/cargo_width/cargo_height] [новое значение]

                Удаление модели машины:
                car delete [ID машины]

                Отображение всех моделей посылок:
                pack list

                Добавление новой модели посылки:
                pack insert [название] [код] [схема] [ширина] [высота]

                Обновление модели посылки:
                pack update [ID посылки] [параметр: name/code/scheme/scheme_width/scheme_height] [новое значение]

                Удаление модели посылки:
                pack delete [ID посылки]""";
        return convertStringToTelegramCodeStyle(info);
    }

    public String toStringIncorrectCommand() {
        return convertStringToTelegramCodeStyle("Не удалось определить введенную команду");
    }

    public String convertStringToTelegramCodeStyle(String text) {
        return "```Ответ:\n" + text + "```";
    }
}
