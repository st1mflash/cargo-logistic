package com.ansekolesnikov.cargologistic.service.telegram;

import org.springframework.stereotype.Component;

@Component
public class TelegramServiceUtils {
    public String toStringBotInfo() {
        String info = "" +
                "Доступные команды:" +
                "\n\nЗагрузка машин данными из файла:" +
                "\nload_file [название файла] [алгоритм: max/half/type] [кол-во машин]" +
                "\n\nЗагрузка машин введенными данными:" +
                "\nload_list [модель машины] [алгоритм: max/half/type] [кол-во машин]:" +
                "\n[модель посылки]" +
                "\n[модель посылки]" +
                "\n[ ... ]" +
                "\n\nОтображение из файла загруженных машин:" +
                "\nview_file [название файла]" +
                "\n\nОтображение всех моделей машин:" +
                "\ncar list" +
                "\n\nДобавление новой модели машины:" +
                "\ncar insert [название] [ширина кузова] [высота кузова]" +
                "\n\nОбновление модели машины:" +
                "\ncar update [ID машины] [параметр: name/cargo_width/cargo_height] [новое значение]" +
                "\n\nУдаление модели машины:" +
                "\ncar delete [ID машины]" +
                "\n\nОтображение всех моделей посылок:" +
                "\npack list" +
                "\n\nДобавление новой модели посылки:" +
                "\npack insert [название] [код] [ширина] [высота]" +
                "\n\nОбновление модели посылки:" +
                "\npack update [ID посылки] [параметр: name/code/scheme/scheme_width/scheme_height] [новое значение]" +
                "\n\nУдаление модели посылки:" +
                "\npack delete [ID посылки]";
        return formatToCodeStyle(info);
    }

    public String formatToCodeStyle(String text) {
        return "```Ответ:\n" + text + "```";
    }
}
