package com.ansekolesnikov.cargologistic.pages;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Data
public class TelegramPages {
    private final TelegramMenuPage telegramMenuPage;
    private final TelegramResultPage telegramResultPage;
    private final TelegramGetIdPage telegramGetIdPage;
    private final TelegramGetNamePage telegramGetNamePage;
    private final TelegramGetCodePage telegramGetCodePage;
    private final TelegramGetSchemePage telegramGetSchemePage;
    private final TelegramGetWidthPage telegramGetWidthPage;
    private final TelegramGetHeightPage telegramGetHeightPage;
    private final TelegramGetParamNamePage telegramGetParamNamePage;
    private final TelegramGetParamValuePage telegramGetParamValuePage;
}
