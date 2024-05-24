package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;

public interface RunnableService {
    ServiceOutput runService(ServiceInput serviceInput);
}
