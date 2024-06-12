package com.ansekolesnikov.cargologistic.mappers;

import com.ansekolesnikov.cargologistic.entity.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.enums.ServiceCommandEnum;
import com.ansekolesnikov.cargologistic.utils.EntityUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Mapper
public interface TelegramMessageMapper {
    @Mapping(target = "message", expression = "java(message)")
    @Mapping(target = "chatId", expression = "java(message.getChatId())")
    @Mapping(target = "text", expression = "java(message.getText())")
    @Mapping(target = "command", expression = "java(getServiceCommandEnum(message))")
    TelegramUserMessage toTelegramUserMessage(Message message);

    default ServiceCommandEnum getServiceCommandEnum(Message message) {
        return EntityUtils.getServiceCommandEnum(message.getText().split(" ")[0]);
    }
}
