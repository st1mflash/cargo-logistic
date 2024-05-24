package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;

public interface EntityService {
    ServiceOutput listOperation();
    ServiceOutput getOperation(ServiceInput command);
    ServiceOutput insertOperation(ServiceInput command);
    ServiceOutput updateOperation(ServiceInput command);
    ServiceOutput deleteOperation(ServiceInput command);
}
