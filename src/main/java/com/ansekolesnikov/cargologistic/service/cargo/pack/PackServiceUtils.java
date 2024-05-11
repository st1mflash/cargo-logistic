package com.ansekolesnikov.cargologistic.service.cargo.pack;

import com.ansekolesnikov.cargologistic.model.command.pack.PackCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.Pack;

public class PackServiceUtils {
    public Pack createPackFromCommand(PackCommandLine command){
        return new Pack(
                command.getNamePack(),
                command.getWidthSchemePack(),
                command.getHeightSchemePack(),
                command.getSchemePack(),
                command.getCodePack()
        );
    }
}
