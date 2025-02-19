package com.thomashaughton.dizplaipoll.dto.response;

import java.time.ZonedDateTime;
import java.util.Map;

public record ErrorResponse(
        ZonedDateTime timestamp,
        Integer status,
        String error,
        String message,
        Map<String, String> validationErrors
) {
}
