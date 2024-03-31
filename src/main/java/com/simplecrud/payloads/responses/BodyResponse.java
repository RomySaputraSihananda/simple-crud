package com.simplecrud.payloads.responses;

public record BodyResponse<Data>(
        String status,
        int code,
        String message,
        Data data
) {}