package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceRequest;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;

public interface IRunnableByStringService {
    String runService(String request);
}
