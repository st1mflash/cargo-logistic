package com.ansekolesnikov.cargologistic.service.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class ResultServiceRun {
    private ResultCarServiceRun resultCarServiceRun = new ResultCarServiceRun();
    private ResultPackServiceRun resultPackServiceRun = new ResultPackServiceRun();
    private ResultLoadFileServiceRun resultLoadFileServiceRun = new ResultLoadFileServiceRun();
    private ResultLoadListServiceRun resultLoadListServiceRun = new ResultLoadListServiceRun();
    private ResultViewFileServiceRun resultViewFileServiceRun = new ResultViewFileServiceRun();

    private String textResult;
    private Map<String, String> mapResult;
    private List<Map<String, String>> listMapResult;
    public ResultServiceRun(String result){
        this.textResult = result;
    }
}
