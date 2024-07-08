package com.ansekolesnikov.cargologistic.entity;

import lombok.Data;

@Data
public class LocalFile {
    private String name;
    private String format;
    private String path;
    private String content;
}
