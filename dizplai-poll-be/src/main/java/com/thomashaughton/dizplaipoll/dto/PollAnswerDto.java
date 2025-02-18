package com.thomashaughton.dizplaipoll.dto;

import java.util.UUID;

public record PollAnswerDto(
        UUID id,
        String answer
) {
}
