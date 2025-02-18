package com.thomashaughton.dizplaipoll.dto.request;

import com.thomashaughton.dizplaipoll.dto.PollAnswerDto;

import java.util.List;

public record PollCreationRequestDto(
        String question,
        List<PollAnswerDto> pollAnswers
) {
}
