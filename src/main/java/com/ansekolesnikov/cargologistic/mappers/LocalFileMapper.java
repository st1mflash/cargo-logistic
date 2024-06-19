package com.ansekolesnikov.cargologistic.mappers;

import com.ansekolesnikov.cargologistic.entity.LocalFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@Mapper
public interface LocalFileMapper {
    @Mapping(target = "name", expression = "java(getLocalFileName(filePath))")
    @Mapping(target = "format", expression = "java(getLocalFileFormat(filePath))")
    @Mapping(target = "path", expression = "java(getLocalFilePath(filePath))")
    @Mapping(target = "content", expression = "java(getLocalFileContent(getLocalFilePath(filePath), getLocalFileName(filePath), getLocalFileFormat(filePath)))")
    LocalFile toLocalFile(String filePath);

    default String getLocalFileName(String filePath) {
        return filePath.substring(filePath.lastIndexOf('/') + 1, filePath.lastIndexOf('.'));
    }

    default String getLocalFileFormat(String filePath) {
        return filePath.substring(filePath.lastIndexOf('.'));
    }

    default String getLocalFilePath(String filePath) {
        return filePath.substring(0, filePath.lastIndexOf('/') + 1);
    }

    default String getLocalFileContent(String path, String name, String format) {
        try {
            return Files.readString(Paths.get(path + name + format));
        } catch (IOException ignored) {
            return null;
        }
    }
}
