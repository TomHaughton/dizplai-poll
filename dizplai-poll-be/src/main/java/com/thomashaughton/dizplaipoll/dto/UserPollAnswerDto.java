package com.thomashaughton.dizplaipoll.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public record UserPollAnswerDto(
        UUID pollId,
        UUID selectedAnswerId,
        UUID userAnswerId,
        ZonedDateTime createdAt
) {
}
