package com.ansekolesnikov.cargologistic.model.file;

public class LocalFileUtils {
    public String getFullAddress(LocalFile localFile) {
        return localFile.getPath() + localFile.getName() + localFile.getFormat();
    }
}
