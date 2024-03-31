package com.simplecrud.payloads.responses;

public record ExceptionResponse(
    String path, 
    String ctx
) {}
