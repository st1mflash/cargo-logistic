package com.ansekolesnikov.cargologistic.mappers;

import com.ansekolesnikov.cargologistic.enums.ServiceCommandEnum;
import com.ansekolesnikov.cargologistic.utils.EntityUtils;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Mapper
public interface TelegramMessageMapper {
    default ServiceCommandEnum getServiceCommandEnum(Message message) {
        return EntityUtils.getServiceCommandEnum(message.getText().split(" ")[0]);
    }
}
