package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.model.command.pack.PackCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class PackServiceUtils {
    public PackModel createPackFromCommand(PackCommandLine command){
        return new PackModel(
                command.getNamePack(),
                command.getWidthSchemePack(),
                command.getHeightSchemePack(),
                command.getSchemePack(),
                command.getCodePack()
        );
    }
}
