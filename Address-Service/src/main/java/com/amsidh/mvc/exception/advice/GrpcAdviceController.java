package com.amsidh.mvc.exception.advice;

import com.amsidh.mvc.exception.AddressNotFoundException;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcAdviceController {

    @GrpcExceptionHandler({RuntimeException.class})
    public Status handleInternalError(RuntimeException runtimeException) {
        return Status.INTERNAL.withDescription(runtimeException.getMessage());
    }

    @GrpcExceptionHandler({AddressNotFoundException.class})
    public Status handleAddressNotFoundException(AddressNotFoundException addressNotFoundException) {
        return Status.NOT_FOUND.withDescription(addressNotFoundException.getMessage());
    }

}