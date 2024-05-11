package com.ansekolesnikov.cargologistic.model.pack;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@NoArgsConstructor
@Component
public class PackUtils {
    public int calcPackageWidthByType(int packType) {
        return switch (packType) {
            case 1, 2, 3, 4, 5 -> packType;
            case 6, 9 -> 3;
            case 7, 8 -> 4;
            default -> 0;
        };
    }

    public String toStringPackInfo(Pack pack) {
        return "Идентификатор: #" + pack.getId()
                + "\nНазвание посылки: " + pack.getName()
                + "\nПараметры посылки: " + pack.getWidth() + "x" + pack.getHeight()
                + "\nСхема посылки:\n"
                + toStringPackScheme(pack);
    }

    public String toStringPackScheme(Pack pack) {
        StringBuilder packSchemeToString = new StringBuilder();
        String packScheme = pack.getScheme();
        for (int i = pack.getHeight() - 1; i >= 0; i--) {
            packSchemeToString.append("+");
            for (int j = 0; j < pack.getWidth(); j++) {
                if (Objects.equals("" + packScheme.charAt(i * pack.getWidth() + j), "0")) {
                    packSchemeToString.append(" ");
                } else {
                    packSchemeToString.append(pack.getCode());
                }
            }
            packSchemeToString.append("+\n");
        }
        packSchemeToString.append("+".repeat(Math.max(0, pack.getWidth() + 2)));
        return packSchemeToString.toString();
    }
}
