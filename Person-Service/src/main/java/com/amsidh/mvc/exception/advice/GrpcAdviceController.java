package com.amsidh.mvc.exception.advice;

import com.amsidh.mvc.exception.PersonNotFoundException;
import io.grpc.Status;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcAdviceController {

    @GrpcExceptionHandler({RuntimeException.class})
    public Status handleInternalError(RuntimeException runtimeException) {
        return Status.INTERNAL.withDescription(runtimeException.getMessage());
    }

    @GrpcExceptionHandler({PersonNotFoundException.class})
    public Status handlePersonNotFoundException(PersonNotFoundException personNotFoundException) {
        return Status.NOT_FOUND.withDescription(personNotFoundException.getMessage());
    }

}