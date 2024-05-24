package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.database.DatabaseMigration;
import com.ansekolesnikov.cargologistic.database.DatabaseConnect;
import com.ansekolesnikov.cargologistic.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {
    @Value("${telegram.bot.username}")
    private String TELEGRAM_BOT_USERNAME;
    @Value("${telegram.bot.token}")
    private String TELEGRAM_BOT_TOKEN;
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String DB_USERNAME;
    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;

    @Bean
    public DatabaseConnect databaseConnect() {
        DatabaseConnect databaseConnect = new DatabaseConnect(DB_URL, DB_USERNAME, DB_PASSWORD);
        new DatabaseMigration(DB_URL, DB_USERNAME, DB_PASSWORD);
        return databaseConnect;
    }

    @Bean
    public TelegramBotService telegramBotService() {
        TelegramBotService telegramBotService = new TelegramBotService();
        telegramBotService.startBot(TELEGRAM_BOT_TOKEN, TELEGRAM_BOT_USERNAME);
        return telegramBotService;
    }
}
