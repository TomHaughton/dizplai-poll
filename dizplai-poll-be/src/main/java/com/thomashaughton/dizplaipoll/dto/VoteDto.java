package com.thomashaughton.dizplaipoll.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public record VoteDto(
        UUID id,
        UUID pollId,
        UUID selectedAnswerId,
        ZonedDateTime createdAt
) {
}
