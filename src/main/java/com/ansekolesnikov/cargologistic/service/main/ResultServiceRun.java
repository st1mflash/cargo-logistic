package com.ansekolesnikov.cargologistic.service.main;

import com.ansekolesnikov.cargologistic.service.main.car.ResultCarServiceRun;
import com.ansekolesnikov.cargologistic.service.main.load.file.ResultLoadFileServiceRun;
import com.ansekolesnikov.cargologistic.service.main.load.list.ResultLoadListServiceRun;
import com.ansekolesnikov.cargologistic.service.main.pack.ResultPackServiceRun;
import com.ansekolesnikov.cargologistic.service.main.view.ResultViewFileServiceRun;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResultServiceRun {
    private ResultCarServiceRun resultCarServiceRun = new ResultCarServiceRun();
    private ResultPackServiceRun resultPackServiceRun = new ResultPackServiceRun();
    private ResultLoadFileServiceRun resultLoadFileServiceRun = new ResultLoadFileServiceRun();
    private ResultLoadListServiceRun resultLoadListServiceRun = new ResultLoadListServiceRun();
    private ResultViewFileServiceRun resultViewFileServiceRun = new ResultViewFileServiceRun();
}
