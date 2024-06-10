package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceRequest;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;

public interface EntityService {
    ServiceOutput listOperation();
    ServiceOutput getOperation(ServiceRequest command);
    ServiceOutput insertOperation(ServiceRequest command);
    ServiceOutput updateOperation(ServiceRequest command);
    ServiceOutput deleteOperation(ServiceRequest command);
}
