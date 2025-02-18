package com.thomashaughton.dizplaipoll.dto;

import java.util.List;
import java.util.UUID;

public record PollDto(
        UUID id,
        String question,
        List<PollAnswerDto> answers
) {
}
