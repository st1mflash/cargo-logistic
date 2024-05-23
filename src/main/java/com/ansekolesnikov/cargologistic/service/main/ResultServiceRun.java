package com.ansekolesnikov.cargologistic.service.main;

import com.ansekolesnikov.cargologistic.service.main.load.file.ResultLoadFileServiceRun;
import com.ansekolesnikov.cargologistic.service.main.load.list.ResultLoadListServiceRun;
import com.ansekolesnikov.cargologistic.service.main.pack.ResultPackServiceRun;
import com.ansekolesnikov.cargologistic.service.main.view.ResultViewFileServiceRun;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class ResultServiceRun {
    private ResultLoadFileServiceRun resultLoadFileServiceRun = new ResultLoadFileServiceRun();
    private ResultLoadListServiceRun resultLoadListServiceRun = new ResultLoadListServiceRun();
    private ResultViewFileServiceRun resultViewFileServiceRun = new ResultViewFileServiceRun();

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
