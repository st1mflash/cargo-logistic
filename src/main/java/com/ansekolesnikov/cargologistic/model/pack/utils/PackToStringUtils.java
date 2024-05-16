package com.ansekolesnikov.cargologistic.model.pack.utils;

import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@NoArgsConstructor
@Component
public class PackToStringUtils {
    public String toStringPackInfo(PackModel packModel) {
        return "Идентификатор: #" + packModel.getId()
                + "\nНазвание посылки: " + packModel.getName()
                + "\nПараметры посылки: " + packModel.getWidth() + "x" + packModel.getHeight()
                + "\nСхема посылки:\n"
                + toStringPackScheme(packModel);
    }

    public String toStringPackScheme(PackModel packModel) {
        StringBuilder packSchemeToString = new StringBuilder();
        String packScheme = packModel.getScheme();
        for (int i = packModel.getHeight() - 1; i >= 0; i--) {
            packSchemeToString.append("+");
            for (int j = 0; j < packModel.getWidth(); j++) {
                if (Objects.equals("" + packScheme.charAt(i * packModel.getWidth() + j), "0")) {
                    packSchemeToString.append(" ");
                } else {
                    packSchemeToString.append(packModel.getCode());
                }
            }
            packSchemeToString.append("+\n");
        }
        packSchemeToString.append("+".repeat(Math.max(0, packModel.getWidth() + 2)));
        return packSchemeToString.toString();
    }
}
