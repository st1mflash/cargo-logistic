package com.ansekolesnikov.cargologistic.service.service_output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class ServiceOutput {
    private String resultToString;
    private Map<String, String> resultToMap;
    private List<Map<String, String>> resultToListMap;

    public void setText(String text) {
        this.resultToString = text;
    }

    public String toString() {
        return resultToString;
    }

    public Map<String, String> toMap() {
        return resultToMap;
    }

    public List<Map<String, String>> toListMap() {
        return resultToListMap;
    }
}
