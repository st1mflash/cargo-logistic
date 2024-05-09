package com.ansekolesnikov.cargologistic.service.telegram;

import com.ansekolesnikov.cargologistic.model.telegram.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.service.ServiceUtils;
import org.springframework.stereotype.Component;

@Component
public class TelegramServiceUtils extends ServiceUtils {
    public String getStringParams(TelegramUserMessage message) {
        return message.getInputFileName() + " " + message.getInputAlgorithm() + " " + message.getInputCountCars();
    }
    public String formatToCodeStyle(String text) {
        return "```Ответ:\n" + text + "```";
    }

    public String getPackParamsFromString(TelegramUserMessage message){
        return message.getInputOperation()
                + " " + message.getInputPackName()
                + " " + message.getInputPackCode()
                + " " + message.getInputPackWidth()
                + " " + message.getInputPackHeight()
                + " " + message.getInputPackScheme();
    }
}
