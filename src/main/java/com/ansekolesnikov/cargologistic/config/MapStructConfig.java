package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.mappers.CarModelMapper;
import com.ansekolesnikov.cargologistic.mappers.LocalFileMapper;
import com.ansekolesnikov.cargologistic.mappers.PackModelMapper;
import com.ansekolesnikov.cargologistic.mappers.TelegramMessageMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public CarModelMapper carModelMapper() {
        return Mappers.getMapper(CarModelMapper.class);
    }

    @Bean
    public PackModelMapper packModelMapper() {
        return Mappers.getMapper(PackModelMapper.class);
    }

    @Bean
    public LocalFileMapper localFileMapper() {
        return Mappers.getMapper(LocalFileMapper.class);
    }

    @Bean
    public TelegramMessageMapper telegramMessageMapper() {
        return Mappers.getMapper(TelegramMessageMapper.class);
    }
}
